
package ci;

/**
 *
 * @author peter
 */
import java.io.*;
import java.net.URL;
import java.util.TreeMap;
import java.util.Vector;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import y.ylib.ylib;
public class CISoundThread extends Thread{

    SourceDataLine line;
    AudioInputStream ais;
    AudioFormat af;
    Vector waitV=new Vector();
    TreeMap mp3NameMap=new TreeMap();
    CrInstrument instrument;
    boolean stopHit=false,pauseHit=false,isSleep=false,hasNew=false;
    private int lastFrame;
    JTextField tf,times,interval;
    String lastSoundFile="";

    AudioFormat targetFormat; 
    AudioInputStream in;
    AudioInputStream din;
    public CISoundThread(CrInstrument instrument){
        this.instrument=instrument;
    }
    public static void main(String args[]){
        CISoundThread sThread=new CISoundThread(null);
        sThread.init();
        sThread.start();
    }
    public void init(){
        JFrame frame=new JFrame();
        frame.setSize(800,200);
        frame.setLayout(null);
        JButton startButton=new JButton("play");
        JButton pauseButton=new JButton("pause");
        JButton continueButton=new JButton("continue");
        JButton stopButton=new JButton("stop");
        frame.add(startButton);
        frame.add(pauseButton);
        frame.add(continueButton);
        frame.add(stopButton);
        tf=new JTextField("C:\\Windows\\Media\\Alarm05.wav");
        times=new JTextField("1");
        frame.add(tf);
        frame.add(times);
        JLabel label=new JLabel("sound file:");
        frame.add(label);
        JLabel label2=new JLabel("times:");
        frame.add(label2);
        JLabel label3=new JLabel("time interval:");
        interval=new JTextField("0");
        JLabel label4=new JLabel("sec");
        frame.add(label3);
        frame.add(interval);
        frame.add(label4);
        label.setBounds(5,15,100,25);
        tf.setBounds(105, 15, 300, 25);
        label2.setBounds(410, 15,55, 25);
        times.setBounds(470, 15, 35, 25);
        label3.setBounds(510, 15, 90, 25);
        interval.setBounds(605, 15, 40, 25);
        label4.setBounds(650, 15, 30, 25);
        startButton.setBounds(5, 45, 90, 20);
        pauseButton.setBounds(105, 45, 90, 20);
        continueButton.setBounds(210, 45, 90, 20);
        stopButton.setBounds(315, 45, 90, 20);

        frame.setVisible(true);
       startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               startActionPerformed(evt);
            }
        });
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               pauseActionPerformed(evt);
            }
        });
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               stopActionPerformed(evt);
            }
        });
         continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               stopActionPerformed(evt);
            }
        });
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

    }
    public void run(){
        while(true){
            isSleep=false;
            while(waitV.size()>0){
                String info[]=ylib.csvlinetoarray((String)waitV.get(0));
                pauseHit=false;
                stopHit=false;

                if(info[0].equals("1")){
                    if(info[1].toLowerCase().endsWith(".mp3")) setMp3Sound(info[1],(info.length>2 && info[2].length()>0? Integer.parseInt(info[2]):1));
                    else setNonMp3Sound(info[1],(info.length>2 && info[2].length()>0? Integer.parseInt(info[2]):1));
                } else if(info[0].equals("2")){
                    if(info.length>2 && info[2].length()>0) {
                         if(info[2].toLowerCase().endsWith(".mp3")) setStart2(info[2],(info.length>1 && info[1].length()>0? Integer.parseInt(info[1]):1),(info.length>3 && info[3].length()>0? Double.parseDouble(info[3]):0.0),(info.length>4 && info[4].length()>0? Integer.parseInt(info[4]):1));
                          else setStart(info[2],(info.length>1 && info[1].length()>0? Integer.parseInt(info[1]):1),(info.length>3 && info[3].length()>0? Double.parseDouble(info[3]):0.0),(info.length>4 && info[4].length()>0? Integer.parseInt(info[4]):1));
                    }
                } else if(info[0].equals("3")){

                } else if(info[0].equals("4")){

                } else if(info[0].equals("5")){

                }

               if(hasNew) {while(waitV.size()>1) waitV.remove(0); hasNew=false;}
               else {while(waitV.size()>0) waitV.remove(0);}
            }

            try{
                isSleep=true;
                sleep(1000L * 60L * 60L * 24L * 365L);
            }catch(InterruptedException e){

            }
        }
    }
    void startActionPerformed(java.awt.event.ActionEvent evt){

        pauseHit=false;
        stopHit=false;
        setAction("2,"+times.getText().trim()+","+tf.getText().trim()+","+interval.getText().trim()+",1");
    }
    void pauseActionPerformed(java.awt.event.ActionEvent evt){
        setPause();
    }
    void stopActionPerformed(java.awt.event.ActionEvent evt){
        setStop(2);
    }
    void continueActionPerformed(java.awt.event.ActionEvent evt){
        setContinue(2);
    }

    public void setNonMp3Sound(String soundFile,int type){
     soundFile=ylib.replace(soundFile, "/", File.separator);
     soundFile=ylib.replace(soundFile, "\\", File.separator);
        try {

            if(mp3NameMap.get(soundFile)!=null) soundFile=(String)mp3NameMap.get(soundFile);
            File sFile=new File(soundFile);
            AudioFormat fmt=AudioSystem.getAudioFileFormat(sFile).getFormat();
            AudioInputStream fis = AudioSystem.getAudioInputStream(sFile);

            if(soundFile.toLowerCase().endsWith(".au")) ais = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,fis);
            else ais = AudioSystem.getAudioInputStream(fmt,fis);
             af = ais.getFormat();
             line = AudioSystem.getSourceDataLine(af);

             line.addLineListener(new MyLineListener());

             line.open(af);
             lastSoundFile=soundFile;

        } catch(UnsupportedAudioFileException e){
            e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "UnsupportedAudioFileException, sound file="+soundFile);
            else instrument.sysLog("UnsupportedAudioFileException, sound file="+soundFile);
        }catch(LineUnavailableException e){
               e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "LineUnavailableException, sound file="+soundFile);
            else instrument.sysLog("LineUnavailableException, sound file="+soundFile);
         } catch(IllegalArgumentException e){
             e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "IllegalArgumentException, sound file="+soundFile+", message="+e.getMessage());
            else instrument.sysLog("IllegalArgumentException, sound file="+soundFile+", message="+e.getMessage());
         }catch(FileNotFoundException e){
             e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "FileNotFoundException, sound file="+soundFile);
            else instrument.sysLog("FileNotFoundException, sound file="+soundFile);
         }catch (Exception e) {

           e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "Exception, sound file="+soundFile+", message="+e.getMessage());
            else instrument.sysLog("Exception, sound file="+soundFile+", message="+e.getMessage());
        }
    }
    public void setAction(String action){
        String info[]=ylib.csvlinetoarray(action);
        if(info.length>0 && info[0].equals("2")){
          if(line!=null && line.isRunning()){
             setStop(1);

          } 

          if(waitV.size()>0) hasNew=true;
        }
        waitV.add(action);

        if(isSleep) interrupt();
    }

    public void setStart(String soundfile,int times,double intervalSec,int type){

        long intervalMs=(long)(intervalSec * 1000.0);
        for(int i=0;i<times;i++){

           try{

            if(line==null) {
                if(true) setNonMp3Sound(soundfile,type);
                else {
                  try{

                    line = AudioSystem.getSourceDataLine(af);

                    line.addLineListener(new MyLineListener());
                    line.open(af);
                  }catch(LineUnavailableException e){
                      e.printStackTrace();
                  }
                }
            }
            int bufSize = line.getBufferSize();
            line.start();

             byte[] data = new byte[bufSize];
             int bytesRead;

             while ((bytesRead = ais.read(data,0,data.length)) != -1)
                if(!stopHit && !pauseHit) line.write(data,0,bytesRead);
                else break;
            if(line!=null) line.drain();
            if(line!=null)  line.stop();
            if(line!=null) line.close();
            line=null;
           } catch(IOException e){
               e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "IOException, sound file="+soundfile);
            else instrument.sysLog("IOException, sound file="+soundfile);
           } 

           if(stopHit || pauseHit) break;
        try{
        Thread.sleep(intervalMs);
        } catch(InterruptedException e){}
        if(stopHit || pauseHit) break;
        }
    }
    public void setPause(){
                        if (line!=null && line.isRunning()) {

                            line.stop();
                            pauseHit=true;
                            hasNew=false;
                        } 
    }

    public void setStop(int type){

        if(line!=null) line.stop();
        if(line!=null) line.close();
        line=null;
        stopHit=true;
        hasNew=false;

    }

    public void setContinue(int type){
        

      if(line!=null) line.start();
    }

 public void setMp3Sound(String filename,int type){
  filename=ylib.replace(filename, "/", File.separator);
  filename=ylib.replace(filename, "\\", File.separator);
  try {
    File file = new File(filename);
    in= AudioSystem.getAudioInputStream(file);

    AudioFormat baseFormat = in.getFormat();
    targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
                                                                                  baseFormat.getSampleRate(),
                                                                                  16,
                                                                                  baseFormat.getChannels(),
                                                                                  baseFormat.getChannels() * 2,
                                                                                  baseFormat.getSampleRate(),
                                                                                  false);
    din = AudioSystem.getAudioInputStream(targetFormat, in);

  } catch(UnsupportedAudioFileException e){
            e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "UnsupportedAudioFileException, sound file="+filename);
            else instrument.sysLog("UnsupportedAudioFileException, sound file="+filename);
  }catch (FileNotFoundException e)  {
        e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "FileNotFoundException, sound file="+filename);
            else instrument.sysLog("FileNotFoundException, sound file="+filename);
    } catch (Exception e)  {
        e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "Exception, sound file="+filename+", message="+e.getMessage());
            else instrument.sysLog("Exception, sound file="+filename+", message="+e.getMessage());
    }
} 

private void setStart2(String soundfile,int times,double intervalSec,int type) {
        long intervalMs=(long)(intervalSec * 1000.0);
        for(int i=0;i<times;i++){

            if(line==null) {
                if(true) setMp3Sound(soundfile,type);
                else {

                }
            }
 byte[] data = new byte[4096];
  try{
  line = getLine(targetFormat); 
  if (line != null)
  {

    line.start();
    int nBytesRead = 0, nBytesWritten = 0;
    while (nBytesRead != -1)
    {
        nBytesRead = din.read(data, 0, data.length);
        if (nBytesRead != -1 && !stopHit && !pauseHit) nBytesWritten = line.write(data, 0, nBytesRead);
    }

            if(line!=null) line.drain();
            if(line!=null)  line.stop();
            if(line!=null) line.close();
            line=null;
    if(din!=null) din.close();
    if(in!=null) in.close();
  } 
  }catch(LineUnavailableException e){
      e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "LineUnavailableException, sound file="+soundfile);
            else instrument.sysLog("LineUnavailableException, sound file="+soundfile);
  }catch(IOException e){
      e.printStackTrace();
            if(type==2) JOptionPane.showMessageDialog(null, "IOException, sound file="+soundfile);
            else instrument.sysLog("IOException, sound file="+soundfile);
  }
  if(stopHit || pauseHit) break;
        try{
        Thread.sleep(intervalMs);
        } catch(InterruptedException e){}
   if(stopHit || pauseHit) break;
}
}
private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
{
  SourceDataLine res = null;
  DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
  res = (SourceDataLine) AudioSystem.getLine(info);
  res.open(audioFormat);
  return res;
} 
    class MyLineListener implements LineListener {
             public void update(LineEvent le) {
                 LineEvent.Type type = le.getType();

             }
    }
}