
package ci;

import java.awt.Toolkit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import y.ende2003.*;
import y.base64.*;
import y.ylib.*;
import golib.*;
import infinity.server.GroupInfo;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import wsn.WSNApplication;
import wsn.*;
import java.text.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Position;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Administrator
 */
public class CrInstrument extends WSNApplication implements Runnable {
  public static String version = "2.17.0020";
  public ResourceBundle bundle2 = java.util.ResourceBundle.getBundle("ci/Bundle");
  String versionTime = "20170623-080100 ", propFile = "apps" + File.separator + "cr-wsn" + File.separator + "ci_pro.txt", newversion = "",
          stationFile = "apps" + File.separator + "cr-wsn" + File.separator + "ci_stations.txt",
          sensorFile = "apps" + File.separator + "cr-wsn" + File.separator + "ci_sensors.txt",currentViewDSrc="",
          statusFile = System.getProperty("user.home") + File.separator + "ci_status.txt", 
          currentStation = "", currentDevice="",currentModel="",currentSN="",currentDataName="",currentSensorID = "",
          modifyKey = "", preFix = "", previousSensorKey = "", lastSensorKey = "", waitDeviceAddr_c = "", waitKey_m = "",
          eventFile ="apps" + File.separator + "cr-wsn" + File.separator + "ci_event.txt", 
          chartFile ="apps" + File.separator + "cr-wsn" + File.separator + "ci_chart.txt", 
          curveFile ="apps" + File.separator + "cr-wsn" + File.separator + "ci_curve.txt", logFileHead = "sys_log",allItemsName="全部",
          uiFile ="apps" + File.separator + "cr-wsn" + File.separator + "ci_ui.txt", 
          conditionFile="",actionFile="",currentChartPara="",dataDir="ci-data",usedDataDir="",logDir="ci-log",
          smsSpFile="",emailSpFile="";
  String restartStr="";

  JLabel button01;
  CIEditFrame editFrame;
  StringBuffer emailMsg = new StringBuffer(), smsMsg = new StringBuffer();

                 boolean firstEmailMsg = true, firstSmsMsg = true;
   public y.ylib.OpenURL openURL=new y.ylib.OpenURL();
  CIAbout about;
  NumberFormat numberFormat = new DecimalFormat("############0.0################################################");
  MgntDialog mgntDialog;
  UserDialog userDialog;

  CIOptions optionsDialog;
  GraphicsDevice device;
  CIShowDataThread showThread;
  CIFileThread fileThread;
  CIMiscThread miscThread;
  CIEventThread eventThread;
  CIActionThread actionThread;
  ShowStationTableThread showTableThread;
  ShowStationChartThread showChartThread;
  Thread myThread;
  DefaultStyledDocument styleDoc=new DefaultStyledDocument();
  Vector displayV=new Vector();
  double roomValues[] = {0.01, 0.02, 0.03, 0.05, 0.1, 0.15, 0.2, 0.25, 0.5, 1.0, 1.5, 3.0, 10.0, 30.0, 90.0};
  int roomIndex = 7,eventMaxArrCnt=15,condMaxArrCnt=40,actMaxArrCnt=80,chartMaxArrCnt=60,curveMaxArrCnt=60,
          currentChartType=1,n120=1;
  public int showIndex=7,showType=2,maxMainLogLength=100000,maxDSrcLogLength=10000;
  boolean beginTextPane=true,lastIsData=false,beginToReceive=false,needChk=false;
  public String lastDataFid="",lastDataType="",lastDataPortN="",lastDataConnectionId="";
  WSN wn;
  WaitThread waitThread = new WaitThread();
  WaitThread2 waitThread2 = new WaitThread2();
  SaveRecordThread saveRecordThread = new SaveRecordThread();
  Vector waitV = new Vector();
  boolean modifySensors = false, cycleOn = false, connected = false, updateHistoryRecord = false, adminLogin = false, userLogin = false,
          firstRound = true,  changeSensor = false, changeStation = false, isSleep = false, hasNewVersion = false;

  GMailThread2 gm2 = new GMailThread2();

  MultiPanel2 multiPanel;
  CIFramePanel framePanel=new CIFramePanel(this);
  CIChartPanel chartPanel=new CIChartPanel(this);
  public TreeMap jClasses=new TreeMap();
  public Hashtable aTime=new Hashtable();
  TreeMap stations = new TreeMap(), sensors = new TreeMap(), allDatum = new TreeMap(), ports = new TreeMap(),
           deviceModuleTM = new TreeMap(), rowToRandomID = new TreeMap(),
          eventTM=new TreeMap(),chartTM=new TreeMap(),curveTM=new TreeMap(),conditionTM=new TreeMap(),actionTM=new TreeMap(),
          dSrcRecord=new TreeMap(),nameIdMap=new TreeMap(),defaultUI=new TreeMap(),currentUI=new TreeMap(),
          smsSpTM=new TreeMap(),emailSpTM=new TreeMap();
  TreeMap deviceToKeys = new TreeMap(), keyToDevices = new TreeMap(),
          portToCoors = new TreeMap(), coorToPorts = new TreeMap(),
          coorDevices = new TreeMap(),
          prefixTM = new TreeMap(), previousTM1 = new TreeMap(), previousTM2 = new TreeMap(), previousTM3 = new TreeMap(),
          currentCalculatedRN = new TreeMap(), deviceToCoor = new TreeMap(),currentDatumTM=new TreeMap(),
          currentConfigTM=new TreeMap(),currentStatusTM=new TreeMap(),allConfigTM=new TreeMap(),allStatusTM=new TreeMap();
  public boolean continueMonitor = false, dataUpdated = false, getDataAfteDeviceFeedBack = false, waitRefresh = false, needUpdateRemoteHistoryFile = false;
  int currentTable2Row = 0, currentStat = 9, currentLightStat = 9,
          currentStat2 = 0, currentLightStat2 = 0;
  public ImageIcon iconRed, iconGray, iconGreen, iconYellow;
  public DefaultListModel stationListModel = new DefaultListModel(),eventListModel = new DefaultListModel(),conditionListModel = new DefaultListModel(),
          actionListModel = new DefaultListModel(),chartListModel = new DefaultListModel(),curveListModel = new DefaultListModel(),
          receiveListModel = new DefaultListModel(),sendListModel = new DefaultListModel(),listModel9 = new DefaultListModel(),
          conditionListModel2 = new DefaultListModel(),actionListModel2 = new DefaultListModel();
  public Image iconImage;
  public Properties props = new Properties(), statuses = new Properties();
  long propUpdateTime = 0, stationUpdateTime = 0, startTime = System.currentTimeMillis(), cycleStartTime = 0, cycleStopTime = 0,
          largestCycleTime = 0, smallestCycleTime = 86400000L, averageCycleTime = 0, cycleCnt = 0, sumCycleTime = 0, lastDataTime = 0,
          lastRecordTime = 0, lastLastDataTime = 0, lastIssueCmdTime_c = 0, lastIssueCmdTime_m = 0, lastReceiveTime = 0, waitDeviceTime_c = 0, waitKeyTime_m = 0;
  static public SimpleDateFormat format3 = new SimpleDateFormat("yyyyMMdd"), format4 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
          format8 = new SimpleDateFormat("yyyy-MM-dd"), format9 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  String apId = "1006",preFrameInfo="";

  int resetCnt = 300,
          waitDeviceAction_c = 0, waitDeviceActionCount_c = 0, waitKeyAction_m = 0, waitKeyActionCount_m = 0;

  Color[] colors={Color.white,Color.red,Color.blue,Color.green,Color.BLACK,Color.CYAN,Color.DARK_GRAY,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.YELLOW};
  double [][] currentPanels={{}};

  public CrInstrument() {

    initComponents();
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int h = Toolkit.getDefaultToolkit().getScreenSize().height - 20;

    int w2 = 1024;
    int h2 = 712;

    setSize(w2, h2);

    setTitle("cr-Instrument " + version);

    setLocation((width - w2) / 2, (h - h2) / 2 - 10);

    iconImage = new ImageIcon(getClass().getClassLoader().getResource("cr_instrument_t.gif")).getImage();
    setIconImage(iconImage);
    init();
  }

  void init() {

    receiveList.setPrototypeCellValue("256.256.256.256-100 yyy.yyy");
    sendList.setPrototypeCellValue("256.256.256.256-100 xxx.xxx");
    receiveList.setFixedCellHeight(18);
    sendList.setFixedCellHeight(18);
    allItemsName=bundle2.getString("CrInstrument.xy.msg100");
    if(!(new File(logDir)).exists())  (new File(logDir)).mkdirs();
    waitThread.start();
    waitThread2.start();
    jComboBox16.insertItemAt("",0);
    jComboBox16.setSelectedIndex(0);
    jPanel72.setVisible(false);
    jPanel124.setVisible(false);
    jComboBox18.insertItemAt("",0);
    jComboBox18.setSelectedIndex(0);
    jPanel79.setVisible(false);
    jPanel78.setVisible(false);
    jPanel85.setVisible(false);
    jPanel126.setVisible(false);
    jPanel26.setVisible(false);

  }

  public void init(WSN wsn) {
    this.wn = wsn;
    makeDataDir();
    nameIdMap.put(allItemsName,"0");
    receiveListModel.addElement(allItemsName);
    init3();
    beginToReceive=true;
    updateList();
    String cmdStr="performmessage wsn.WSN getdatasource ";
    wsn.w.sendToAll(cmdStr);
    jLabel34.setText("");
    jLabel35.setText("");
    myThread = new Thread(this);
    myThread.start();
    

    if (openPorts(2)) {
      lightPanel2.setColor(Color.green, Color.green);
    }
    if (chkProps("monitor-autostart")) {
      pressStartBtn(2);
      

    } else {
      if (!onlyReceiveCB.isSelected()) {
        continueMonitor = false;
      }
      button02.setEnabled(true);
      btnStart.setText(bundle2.getString("CrInstrument.xy.msg3"));

      lightPanel2.setColor(Color.gray, Color.gray);
      currentStat = 9;
      currentLightStat = 9;
    }
    updateHistoryFile(0);
    eventThread.setStatus(wn.w.getGNS(1),"",50);
  }

  void init3() {

    jMenuBar1.add(Box.createHorizontalGlue());
    button01 = new JLabel(" Cloud-Rain ");
    button01.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        menuLabel4MouseClicked(evt);
      }
    });
    jMenuBar1.add(button01);

    read_ci_Props();
    readStatus();

    jTabbedPane1.remove(jPanel3);
    jScrollPane1.getViewport().setBackground(Color.white);
    jScrollPane3.getViewport().setBackground(Color.white);
    jTable1.setShowHorizontalLines(true);
    jTable1.setShowVerticalLines(true);
    jTable2.setShowHorizontalLines(true);
    jTable2.setShowVerticalLines(true);
    jTable2.setDefaultRenderer(Object.class, new MyTableCellRenderer());
    lightPanel2.setColor(Color.gray, Color.gray);
    setSerialPorts();
    setUpSerialPortColumnFromSerialTM(jTable5,jTable5.getColumnModel().getColumn(0));
    readPorts();
    readSensors();
    showThread=new CIShowDataThread(this);
    showThread.start();
    fileThread=new CIFileThread(this);
    fileThread.start();
    miscThread=new CIMiscThread(this);
    miscThread.start();
    eventThread=new CIEventThread(this);
    eventThread.start();
    actionThread=new CIActionThread(this);
    actionThread.start();
    showTableThread=new ShowStationTableThread();
    showTableThread.start();
    showChartThread=new ShowStationChartThread();
    showChartThread.start();
    double panels[][] = {{0.02, 0.02, 0.96, 0.53}, {0.02, 0.57, 0.96, 0.41}};
    multiPanel = new MultiPanel2(Color.white, panels);
    if (OneVar.check(props.getProperty("lky-n"), 1)) {
      jPanel18.add(multiPanel);
    }
    multiPanel.setBounds(5, 20, jPanel18.getWidth() - 10, jPanel18.getHeight() - 25);
    getCurrentRN();
    readDefaultUI();
    readUI();
    readEvents();
    readConditions();
    readActions();
    readCharts();
    readCurves();
    readSmsSp();
    readEmailSp();
    chkAndAdjustEvents();
    saveRecordThread.start();

    if (wn.chkProps("run_my_ap_only") && wn.w.chkValue(props.getProperty("auto-update"))) {
      wn.w.startUpd("http://cloud-rain.com/web/ci_version.txt", apId, version, 10L);

    }
    updateUIFromProps();
    showCondition();
    showAction();
    showEvent();
    updateChartProfile();
    if (stationListModel.getSize() > 0) {
      stationList.setSelectedIndex(0);

      showStation((String) stationListModel.getElementAt(0));
    }
    jPanel140.add(framePanel, BorderLayout.CENTER);
    jPanel142.add(chartPanel,BorderLayout.CENTER);
    framePanel.setItems(currentUI);
    chartPanel.setItems(eventTM);

      JTableHeader th = jTable3.getTableHeader();
      TableColumnModel tcm = th.getColumnModel();
      TableColumn tc = tcm.getColumn(0);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg5"));
      th.repaint();
      th = jTable4.getTableHeader();
      tcm = th.getColumnModel();
      tc = tcm.getColumn(0);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg25"));
      th.repaint();
      th = jTable2.getTableHeader();
      tcm = th.getColumnModel();
      tc = tcm.getColumn(0);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg6"));
      tc = tcm.getColumn(1);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg7"));
      tc = tcm.getColumn(2);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg8"));
      tc = tcm.getColumn(3);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg9"));
      tc = tcm.getColumn(4);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg10"));
      tc = tcm.getColumn(5);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg11"));
      th.repaint();
      th = jTable1.getTableHeader();
      tcm = th.getColumnModel();
      tc = tcm.getColumn(0);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg12"));
      tc = tcm.getColumn(1);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg13"));
      tc = tcm.getColumn(2);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg14"));
      tc = tcm.getColumn(3);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg15"));
      tc = tcm.getColumn(4);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg16"));
      tc = tcm.getColumn(5);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg17"));
      tc = tcm.getColumn(6);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg18"));
      tc = tcm.getColumn(8);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg19"));
      tc = tcm.getColumn(9);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg20"));
      tc = tcm.getColumn(12);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg21"));
      tc = tcm.getColumn(13);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg22"));
      tc = tcm.getColumn(14);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg23"));
      tc = tcm.getColumn(15);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg24"));
      th.repaint();
      th = jTable5.getTableHeader();
      tcm = th.getColumnModel();
      tc = tcm.getColumn(0);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg135"));
      tc = tcm.getColumn(1);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg136"));
      th.repaint();
      th = jTable6.getTableHeader();
      tcm = th.getColumnModel();
      tc = tcm.getColumn(0);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg137"));
      tc = tcm.getColumn(1);
      tc.setHeaderValue( bundle2.getString("CrInstrument.xy.msg138"));
      th.repaint();

      CIDT.EnumOS os=CIDT.getOs();
      if(!os.isWindows()) jMenuItem9.setVisible(false);

    fileUpLoadMenuItem.setVisible(false);
    if(!(System.getProperty("user.language").equalsIgnoreCase("zh") && System.getProperty("user.country").equalsIgnoreCase("TW"))) jMenuItem20.setVisible(false);
    jTabbedPane3.remove(UIPanel);
    jTabbedPane3.remove(NodeMgntPanel);
    CBUseEngineerUnit.setVisible(false);
    jPanel23.setVisible(false);
    jPanel25.setVisible(false);
    FTPPanel.setVisible(false);

    jLabel58.setVisible(false);
    jLabel76.setVisible(false);
    btnZoomIn.setVisible(false);
    btnZoomOut.setVisible(false);
    cbRemark.setVisible(false);

    validate();
    sysLog("System start at " + format4.format(new Date(startTime)) + " (version=" + version + ", version time=" + versionTime + ",ci-demo="+props.getProperty("ci-demo")+", run_my_ap_only="+wn.getPropsString("run_my_ap_only")+")");
  }
  public void setUpSerialPortColumnFromStationTM(JTable table,TableColumn serialColumn){
    JComboBox comboBox = new JComboBox();
    comboBox.setEditable(true);
    comboBox.addItem("");
      Iterator it = stations.keySet().iterator();
      for (; it.hasNext();) {
        String key = (String) it.next();
        String port = (String) stations.get(key);
        if (port.toLowerCase().indexOf("com") == 0) {
          comboBox.addItem(port);
        }
      }
      serialColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for serial port list");
        serialColumn.setCellRenderer(renderer);
  }
  public void setUpSerialPortColumnFromSerialTM(JTable table,TableColumn serialColumn) {

    JComboBox comboBox = new JComboBox();
    comboBox.setEditable(true);
    comboBox.addItem("");

    String portArr[] = WSNSerial.getPorts();
    for (int i = 0; i < portArr.length; i++) {
      String port = portArr[i];
      comboBox.addItem(port);
    }        
    serialColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for serial port list");
        serialColumn.setCellRenderer(renderer);
  }
    public void setUpSocketPortColumnFromStationTM(JTable table,TableColumn socketColumn){
      JComboBox comboBox = new JComboBox();
    comboBox.setEditable(true);
      comboBox.addItem("");
      Iterator it = stations.keySet().iterator();
      for (; it.hasNext();) {
        String key = (String) it.next();
        String port = (String) stations.get(key);
        if (port.toLowerCase().indexOf("com") != 0) {
          comboBox.addItem(port);
        }
            socketColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for socketerial port list");
        socketColumn.setCellRenderer(renderer);
      }
  }
  public void setUpSocketPortColumn(JTable table,TableColumn socketColumn) {

    JComboBox comboBox = new JComboBox();
    comboBox.setEditable(true);
    comboBox.addItem("");

    String portArr[] = WSNSerial.getPorts();
    for (int i = 0; i < portArr.length; i++) {
      String port = portArr[i];
      comboBox.addItem(port);
    }        
    socketColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for socketerial port list");
        socketColumn.setCellRenderer(renderer);
  }

  public void informVersion(String apId, int status, String version) {
    if (apId.equals(this.apId)) {
      if (status == 1 || status == 3) {
        hasNewVersion = true;
        newversion = version;
      }
      

      updateTitle();
    }
  }
  void readEvents() {
    if (wn.w.getValueString(props.getProperty("event-file")).length() > 0) {
      eventFile = wn.w.getValueString(props.getProperty("event-file"));
      eventFile = eventFile.replace('\\', File.separatorChar);
      eventFile=eventFile.replace('/', File.separatorChar);
      if (new File(eventFile).exists()) {
       try {

        FileInputStream in = new FileInputStream(eventFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 0) {
            String info[] = ylib.csvlinetoarray(str1);
            eventTM.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + eventFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + eventFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }

      } else sysLog("event-file "+eventFile+" not found.");
    } else  sysLog("event-file undefied.");
  }
  void readCharts() {
    if (wn.w.getValueString(props.getProperty("chart-file")).length() > 0) {
      chartFile = wn.w.getValueString(props.getProperty("chart-file"));
      chartFile = chartFile.replace('\\', File.separatorChar);
      chartFile=chartFile.replace('/',File.pathSeparatorChar);
      if (new File(chartFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(chartFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 0) {
            String info[] = ylib.csvlinetoarray(str1);
            chartTM.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + chartFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + chartFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("chart file "+chartFile+" not found.");
    } else sysLog("chart-file undefied.");
  }
  void readDefaultUI() {
    if (wn.w.getValueString(props.getProperty("default-ui-file")).length() > 0) {
      String defaultUIFile = wn.w.getValueString(props.getProperty("default-ui-file"));
      defaultUIFile = defaultUIFile.replace('\\', File.separatorChar);
      defaultUIFile=defaultUIFile.replace('/', File.separatorChar);
      if (new File(defaultUIFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(defaultUIFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 0) {
            String info[] = ylib.csvlinetoarray(str1);
            defaultUI.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + defaultUIFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + defaultUIFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("default ui file "+defaultUIFile+" not found.");
    } else sysLog("default-ui-file undefied.");
  }
  void readUI() {
    if (wn.w.getValueString(props.getProperty("ui-file")).length() > 0) {
      uiFile = wn.w.getValueString(props.getProperty("ui-file"));
      uiFile = uiFile.replace('\\', File.separatorChar);
      uiFile=uiFile.replace('/', File.separatorChar);
      if (new File(uiFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(uiFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 0) {
            String info[] = ylib.csvlinetoarray(str1);
            currentUI.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + uiFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + uiFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("ui file "+uiFile+" not found.");
    } else sysLog("ui-file undefied.");
    if(currentUI.size()<1) currentUI=(TreeMap)defaultUI.clone();
  }
  void readCurves() {
    if (wn.w.getValueString(props.getProperty("curve-file")).length() > 0) {
      curveFile = wn.w.getValueString(props.getProperty("curve-file"));
      curveFile = curveFile.replace('\\', File.separatorChar);
      curveFile=curveFile.replace('/',File.separatorChar);
      if (new File(curveFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(curveFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 3) {
            String info[] = ylib.csvlinetoarray(str1);
            curveTM.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + curveFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + curveFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("curve-file "+curveFile+" not found.");
    } else sysLog("curve-file undefied.");
  }
  void readConditions() {
    if (wn.w.getValueString(props.getProperty("condition-file")).length() > 0) {
      conditionFile = wn.w.getValueString(props.getProperty("condition-file"));
      conditionFile = conditionFile.replace('\\', File.separatorChar);
      conditionFile=conditionFile.replace('/', File.separatorChar);
      if (new File(conditionFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(conditionFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 3) {
            String info[] = ylib.csvlinetoarray(str1);

              conditionTM.put(info[0], str1);

          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + conditionFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + conditionFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("condition-file "+conditionFile+" not found.");
    } else sysLog("condition-file undefied.");
  }
  void readActions() {
    if (wn.w.getValueString(props.getProperty("action-file")).length() > 0) {
      actionFile = wn.w.getValueString(props.getProperty("action-file"));
      actionFile = actionFile.replace('\\', File.separatorChar);
      actionFile=actionFile.replace('/', File.separatorChar);
      if (new File(actionFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(actionFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 3) {
            String info[] = ylib.csvlinetoarray(str1);
            actionTM.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + actionFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + actionFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("action-file "+actionFile+" not found.");
    } else sysLog("action-file undefied.");
  }
    void readSmsSp() {
    if (wn.w.getValueString(props.getProperty("sms-sp-file")).length() > 0) {
      smsSpFile = wn.w.getValueString(props.getProperty("sms-sp-file"));
      smsSpFile = smsSpFile.replace('\\', File.separatorChar);
      smsSpFile=smsSpFile.replace('/', File.separatorChar);
      if (new File(smsSpFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(smsSpFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 3) {
            String info[] = ylib.csvlinetoarray(str1);
            smsSpTM.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + smsSpFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + smsSpFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("sms-sp-file "+smsSpFile+" not found.");
    } else sysLog("sms-sp-file undefied.");
  }
    void readEmailSp() {
    if (wn.w.getValueString(props.getProperty("email-sp-file")).length() > 0) {
      emailSpFile = wn.w.getValueString(props.getProperty("email-sp-file"));
      emailSpFile = emailSpFile.replace('\\', File.separatorChar);
      emailSpFile=emailSpFile.replace('/',File.separatorChar);
      if (new File(emailSpFile).exists()) {
        try {

        FileInputStream in = new FileInputStream(emailSpFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 3) {
            String info[] = ylib.csvlinetoarray(str1);
            emailSpTM.put(info[0], str1);
          }
        }
      } catch (IOException e) {

        sysLog("reading file (filename=" + emailSpFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        sysLog("reading file (filename=" + emailSpFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      } else sysLog("email-sp-file "+emailSpFile+" not found.");
    } else sysLog("email-sp-file undefied.");
  }
void updateChartProfile(){

  TreeMap currentChartTM=new TreeMap();
  currentDatumTM.clear();
  currentConfigTM.clear();
  currentStatusTM.clear();
  if(byStation.isSelected()){
    if(stationList.getSelectedValue()!=null){
      String station=(String)stationList.getSelectedValue();

      Iterator it=curveTM.values().iterator();
      for(;it.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it.next());
        if(curveData[4].equalsIgnoreCase(station)){
           String chartData[]=ylib.csvlinetoarray((String)chartTM.get(curveData[3]));
           if(chartData[31].trim().length()<1)  currentChartTM.put(curveData[3], chartTM.get(curveData[3]));
        }
      }
      if(currentChartTM.size()>0){

        int inx=0;
        double pnls[][]=new double[currentChartTM.size()][4];
        Iterator it2=currentChartTM.values().iterator();
        for(;it2.hasNext();){
          String chartData[]=ylib.csvlinetoarray((String)it2.next());
          pnls[inx][0]=Double.parseDouble(chartData[2]);
          pnls[inx][1]=Double.parseDouble(chartData[3]);
          pnls[inx][2]=Double.parseDouble(chartData[4]);
          pnls[inx][3]=Double.parseDouble(chartData[5]);
          Iterator it3=curveTM.values().iterator();
          for(;it3.hasNext();){
            String curveData[]=ylib.csvlinetoarray((String)it3.next());
            if(curveData[4].equals(station) && curveData[3].equals(chartData[0])){
              String actionData[]=ylib.csvlinetoarray((String)actionTM.get(curveData[2]));
              Iterator it4=sensors.values().iterator();
              for(;it4.hasNext();){
                String sensorData[]=ylib.csvlinetoarray((String)it4.next());
                if(sensorData[0].equalsIgnoreCase(station) && sensorData[2].equalsIgnoreCase(actionData[16])){
                  String key=station+","+actionData[39]+","+actionData[16]+"-"+sensorData[2]+"-"+actionData[17];
                  if(allDatum.get(key)==null){
                    allDatum.put(key,new TreeMap());
                  }
                  TreeMap dataTM;
                  if(currentDatumTM.get(chartData[0])==null){
                    dataTM=new TreeMap();
                  } else dataTM=(TreeMap)currentDatumTM.get(chartData[0]);
                  dataTM.put(key,(TreeMap)allDatum.get(key));
                  currentDatumTM.put(chartData[0],dataTM);
                  if(allConfigTM.get(key)==null){
                    allConfigTM.put(key,getConfig(curveData[0]));
                  }
                  TreeMap configTM;
                  if(currentConfigTM.get(chartData[0])==null){
                    configTM=new TreeMap();
                  } else configTM=(TreeMap)currentConfigTM.get(chartData[0]);
                  configTM.put(key,(Config)allConfigTM.get(key));
                  currentConfigTM.put(chartData[0],configTM);
                  if(allStatusTM.get(key)==null){
                    allStatusTM.put(key,getStatus(curveData[0]));
                  }
                  TreeMap statusTM;
                  if(currentStatusTM.get(chartData[0])==null){
                    statusTM=new TreeMap();
                  } else statusTM=(TreeMap) currentStatusTM.get(chartData[0]);
                  statusTM.put(key,(Status)allStatusTM.get(key));
                  currentStatusTM.put(chartData[0],statusTM);
                }
              }
            }
          }
          inx++;
        }
        multiPanel.setPanels(pnls);
        Iterator it5=currentChartTM.values().iterator();
        inx=0;
        for(;it5.hasNext();){
          String chartData[]=ylib.csvlinetoarray((String)it5.next());
          multiPanel.setTMData(inx, (TreeMap)currentDatumTM.get(chartData[0]), (TreeMap)currentConfigTM.get(chartData[0]), (TreeMap)currentStatusTM.get(chartData[0]), "0");
          inx++;
        }
      }
      currentChartPara=station;
    }
    if(currentChartTM.size()>0) return;
  }
  if(byDevice.isSelected()){
    if(stationList.getSelectedValue()!=null && jTable2.getSelectedRow()>-1){
      int sel=jTable2.getSelectedRow();
      String device=(String)jTable2.getModel().getValueAt(sel, 0);
      String model=(String)jTable2.getModel().getValueAt(sel, 1);
      String sn=(String)jTable2.getModel().getValueAt(sel, 2);
      String station=(String)stationList.getSelectedValue();

      Iterator it=curveTM.values().iterator();
      for(;it.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it.next());
        if(curveData[4].equalsIgnoreCase(station) && curveData[10].equalsIgnoreCase(device)){
           String chartData[]=ylib.csvlinetoarray((String)chartTM.get(curveData[3]));
           if(chartData[31].trim().length()<1)  currentChartTM.put(curveData[3], chartTM.get(curveData[3]));
        }
      }
      if(currentChartTM.size()>0){

        int inx=0;
        double pnls[][]=new double[currentChartTM.size()][4];
        Iterator it2=currentChartTM.values().iterator();
        for(;it2.hasNext();){
        String chartData[]=ylib.csvlinetoarray((String)it2.next());
           pnls[inx][0]=Double.parseDouble(chartData[2]);
           pnls[inx][1]=Double.parseDouble(chartData[3]);
           pnls[inx][2]=Double.parseDouble(chartData[4]);
           pnls[inx][3]=Double.parseDouble(chartData[5]);
      Iterator it3=curveTM.values().iterator();
      for(;it3.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it3.next());
        if(curveData[4].equals(station) && curveData[3].equals(chartData[0])){
        String actionData[]=ylib.csvlinetoarray((String)actionTM.get(curveData[2]));
        Iterator it4=sensors.values().iterator();
          for(;it4.hasNext();){
            String sensorData[]=ylib.csvlinetoarray((String)it4.next());
            if(sensorData[0].equalsIgnoreCase(station) && sensorData[1].equalsIgnoreCase(actionData[39]) && sensorData[3].equals(sn)){

              String key=station+","+actionData[39]+","+actionData[16]+"-"+sensorData[2]+"-"+actionData[17];
              if(allDatum.get(key)==null){
                allDatum.put(key,new TreeMap());
              }
              TreeMap dataTM;
              if(currentDatumTM.get(chartData[0])==null){
                dataTM=new TreeMap();
              } else dataTM=(TreeMap)currentDatumTM.get(chartData[0]);
              dataTM.put(key,(TreeMap)allDatum.get(key));
               currentDatumTM.put(chartData[0],dataTM);
              if(allConfigTM.get(key)==null){
                allConfigTM.put(key,getConfig(curveData[0]));
              }
              TreeMap configTM;
              if(currentConfigTM.get(chartData[0])==null){
                configTM=new TreeMap();
              } else configTM=(TreeMap)currentConfigTM.get(chartData[0]);
              configTM.put(key,(Config)allConfigTM.get(key));
               currentConfigTM.put(chartData[0],configTM);
              if(allStatusTM.get(key)==null){
                allStatusTM.put(key,getStatus(curveData[0]));
              }
              TreeMap statusTM;
              if(currentStatusTM.get(chartData[0])==null){
                statusTM=new TreeMap();
              } else statusTM=(TreeMap) currentStatusTM.get(chartData[0]);
              statusTM.put(key,(Status)allStatusTM.get(key));
               currentStatusTM.put(chartData[0],statusTM);
              }
            }
        }
      }
         inx++;
        }
        multiPanel.setPanels(pnls);
        Iterator it5=currentChartTM.values().iterator();
        inx=0;
        for(;it5.hasNext();){
          String chartData[]=ylib.csvlinetoarray((String)it5.next());
          multiPanel.setTMData(inx, (TreeMap)currentDatumTM.get(chartData[0]), (TreeMap)currentConfigTM.get(chartData[0]), (TreeMap)currentStatusTM.get(chartData[0]), "0");
          inx++;
        }
      }
      currentChartPara=device;
    }
    if(currentChartTM.size()>0) return;
  }
  if(byModel.isSelected()){
    if(stationList.getSelectedValue()!=null && jTable2.getSelectedRow()>-1){
      int sel=jTable2.getSelectedRow();
      String device=(String)jTable2.getModel().getValueAt(sel, 0);
      String model=(String)jTable2.getModel().getValueAt(sel, 1);
      String sn=(String)jTable2.getModel().getValueAt(sel, 2);
      String station=(String)stationList.getSelectedValue();

      Iterator it=curveTM.values().iterator();
      for(;it.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it.next());
        if(curveData[4].equalsIgnoreCase(station) && curveData[10].equalsIgnoreCase(device) && curveData[5].equalsIgnoreCase(model)){
           String chartData[]=ylib.csvlinetoarray((String)chartTM.get(curveData[3]));
           if(chartData[31].trim().length()<1)  currentChartTM.put(curveData[3], chartTM.get(curveData[3]));
        }
      }
      if(currentChartTM.size()>0){

        int inx=0;
        double pnls[][]=new double[currentChartTM.size()][4];
        Iterator it2=currentChartTM.values().iterator();
        for(;it2.hasNext();){
        String chartData[]=ylib.csvlinetoarray((String)it2.next());
           pnls[inx][0]=Double.parseDouble(chartData[2]);
           pnls[inx][1]=Double.parseDouble(chartData[3]);
           pnls[inx][2]=Double.parseDouble(chartData[4]);
           pnls[inx][3]=Double.parseDouble(chartData[5]);
      Iterator it3=curveTM.values().iterator();
      for(;it3.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it3.next());
        if(curveData[4].equals(station) && curveData[3].equals(chartData[0])){
        String actionData[]=ylib.csvlinetoarray((String)actionTM.get(curveData[2]));
        Iterator it4=sensors.values().iterator();
          for(;it4.hasNext();){
            String sensorData[]=ylib.csvlinetoarray((String)it4.next());
            if(sensorData[0].equalsIgnoreCase(station) && sensorData[1].equalsIgnoreCase(actionData[16]) && sensorData[2].equals(sn)){

              String key=station+","+actionData[39]+","+actionData[16]+"-"+sensorData[2]+"-"+actionData[17];
              if(allDatum.get(key)==null){
                allDatum.put(key,new TreeMap());
              }
              TreeMap dataTM;
              if(currentDatumTM.get(chartData[0])==null){
                dataTM=new TreeMap();
              } else dataTM=(TreeMap)currentDatumTM.get(chartData[0]);
              dataTM.put(key,(TreeMap)allDatum.get(key));
               currentDatumTM.put(chartData[0],dataTM);
              if(allConfigTM.get(key)==null){
                allConfigTM.put(key,getConfig(curveData[0]));
              }
              TreeMap configTM;
              if(currentConfigTM.get(chartData[0])==null){
                configTM=new TreeMap();
              } else configTM=(TreeMap)currentConfigTM.get(chartData[0]);
              configTM.put(key,(Config)allConfigTM.get(key));
               currentConfigTM.put(chartData[0],configTM);
              if(allStatusTM.get(key)==null){
                allStatusTM.put(key,getStatus(curveData[0]));
              }
              TreeMap statusTM;
              if(currentStatusTM.get(chartData[0])==null){
                statusTM=new TreeMap();
              } else statusTM=(TreeMap) currentStatusTM.get(chartData[0]);
              statusTM.put(key,(Status)allStatusTM.get(key));
               currentStatusTM.put(chartData[0],statusTM);
              }
            }
        }
      }
         inx++;
        }
        multiPanel.setPanels(pnls);
        Iterator it5=currentChartTM.values().iterator();
        inx=0;
        for(;it5.hasNext();){
          String chartData[]=ylib.csvlinetoarray((String)it5.next());
          multiPanel.setTMData(inx, (TreeMap)currentDatumTM.get(chartData[0]), (TreeMap)currentConfigTM.get(chartData[0]), (TreeMap)currentStatusTM.get(chartData[0]), "0");
          inx++;
        }
      }
      currentChartPara=model;
    }
    if(currentChartTM.size()>0) return;
  }
  if(byDataName.isSelected()){
    if(stationList.getSelectedValue()!=null && jTable2.getSelectedRow()>-1){
      int sel=jTable2.getSelectedRow();
      String device=(String)jTable2.getModel().getValueAt(sel, 0);
      String model=(String)jTable2.getModel().getValueAt(sel, 1);
      String sn=(String)jTable2.getModel().getValueAt(sel, 2);
      String station=(String)stationList.getSelectedValue();
      String dataName=(String)jTable2.getModel().getValueAt(sel, 3);

      Iterator it=curveTM.values().iterator();
      for(;it.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it.next());
        if(curveData[4].equalsIgnoreCase(station) && curveData[10].equalsIgnoreCase(device)  && curveData[5].equalsIgnoreCase(model) && curveData[6].equalsIgnoreCase(dataName)){
           String chartData[]=ylib.csvlinetoarray((String)chartTM.get(curveData[3]));
           if(chartData[31].trim().length()<1)  currentChartTM.put(curveData[3], chartTM.get(curveData[3]));
        }
      }
      if(currentChartTM.size()>0){

        int inx=0;
        double pnls[][]=new double[currentChartTM.size()][4];
        Iterator it2=currentChartTM.values().iterator();
        for(;it2.hasNext();){
        String chartData[]=ylib.csvlinetoarray((String)it2.next());
           pnls[inx][0]=Double.parseDouble(chartData[2]);
           pnls[inx][1]=Double.parseDouble(chartData[3]);
           pnls[inx][2]=Double.parseDouble(chartData[4]);
           pnls[inx][3]=Double.parseDouble(chartData[5]);
      Iterator it3=curveTM.values().iterator();
      for(;it3.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it3.next());
        if(curveData[4].equals(station) && curveData[3].equals(chartData[0])){
        String actionData[]=ylib.csvlinetoarray((String)actionTM.get(curveData[2]));
        Iterator it4=sensors.values().iterator();
          for(;it4.hasNext();){
            String sensorData[]=ylib.csvlinetoarray((String)it4.next());
            if(sensorData[0].equalsIgnoreCase(station) && sensorData[1].equalsIgnoreCase(actionData[16]) && sensorData[2].equals(sn) && sensorData[6].equalsIgnoreCase(dataName)){
              String key=station+"-"+actionData[16]+"-"+sensorData[2]+"-"+actionData[0];
              if(allDatum.get(key)==null){
                allDatum.put(key,new TreeMap());
              }
              TreeMap dataTM;
              if(currentDatumTM.get(chartData[0])==null){
                dataTM=new TreeMap();
              } else dataTM=(TreeMap)currentDatumTM.get(chartData[0]);
              dataTM.put(key,(TreeMap)allDatum.get(key));
               currentDatumTM.put(chartData[0],dataTM);
              if(allConfigTM.get(key)==null){
                allConfigTM.put(key,getConfig(curveData[0]));
              }
              TreeMap configTM;
              if(currentConfigTM.get(chartData[0])==null){
                configTM=new TreeMap();
              } else configTM=(TreeMap)currentConfigTM.get(chartData[0]);
              configTM.put(key,(Config)allConfigTM.get(key));
               currentConfigTM.put(chartData[0],configTM);
              if(allStatusTM.get(key)==null){
                allStatusTM.put(key,getStatus(curveData[0]));
              }
              TreeMap statusTM;
              if(currentStatusTM.get(chartData[0])==null){
                statusTM=new TreeMap();
              } else statusTM=(TreeMap) currentStatusTM.get(chartData[0]);
              statusTM.put(key,(Status)allStatusTM.get(key));
               currentStatusTM.put(chartData[0],statusTM);
              }
            }
        }
      }
         inx++;
        }
        multiPanel.setPanels(pnls);
        Iterator it5=currentChartTM.values().iterator();
        inx=0;
        for(;it5.hasNext();){
          String chartData[]=ylib.csvlinetoarray((String)it5.next());
          multiPanel.setTMData(inx, (TreeMap)currentDatumTM.get(chartData[0]), (TreeMap)currentConfigTM.get(chartData[0]), (TreeMap)currentStatusTM.get(chartData[0]), "0");
          inx++;
        }
      }
      currentChartPara=dataName;
    }
    if(currentChartTM.size()>0) return;
  }
  if(byChartGroup.isSelected()){
    if(chartGroupCB.getSelectedItem()!=null && ((String)chartGroupCB.getSelectedItem()).length()>0){
      String chartGroupName=(String)chartGroupCB.getSelectedItem();

      Iterator it=chartTM.values().iterator();
      for(;it.hasNext();){
        String chartData[]=ylib.csvlinetoarray((String)it.next());
        if(chartData[31].equalsIgnoreCase(chartGroupName)){
           currentChartTM.put(chartData[0], chartTM.get(chartData[0]));
        }
      }
      if(currentChartTM.size()>0){

        int inx=0;
        double pnls[][]=new double[currentChartTM.size()][4];
        Iterator it2=currentChartTM.values().iterator();
        for(;it2.hasNext();){
        String chartData[]=ylib.csvlinetoarray((String)it2.next());
           pnls[inx][0]=Double.parseDouble(chartData[2]);
           pnls[inx][1]=Double.parseDouble(chartData[3]);
           pnls[inx][2]=Double.parseDouble(chartData[4]);
           pnls[inx][3]=Double.parseDouble(chartData[5]);
      Iterator it3=curveTM.values().iterator();
      for(;it3.hasNext();){
        String curveData[]=ylib.csvlinetoarray((String)it3.next());
        if(curveData[3].equals(chartData[0])){
        String actionData[]=ylib.csvlinetoarray((String)actionTM.get(curveData[2]));
        Iterator it4=sensors.values().iterator();
          for(;it4.hasNext();){
            String sensorData[]=ylib.csvlinetoarray((String)it4.next());
            if(sensorData[0].equalsIgnoreCase(actionData[1]) && sensorData[1].equalsIgnoreCase(actionData[16]) && curveData[6].equalsIgnoreCase(actionData[17])){
              String key=sensorData[0]+"-"+actionData[16]+"-"+sensorData[2]+"-"+actionData[0];
              if(allDatum.get(key)==null){
                allDatum.put(key,new TreeMap());
              }
              TreeMap dataTM;
              if(currentDatumTM.get(chartData[0])==null){
                dataTM=new TreeMap();
              } else dataTM=(TreeMap)currentDatumTM.get(chartData[0]);
              dataTM.put(key,(TreeMap)allDatum.get(key));
               currentDatumTM.put(chartData[0],dataTM);
              if(allConfigTM.get(key)==null){
                allConfigTM.put(key,getConfig(curveData[0]));
              }
              TreeMap configTM;
              if(currentConfigTM.get(chartData[0])==null){
                configTM=new TreeMap();
              } else configTM=(TreeMap)currentConfigTM.get(chartData[0]);
              configTM.put(key,(Config)allConfigTM.get(key));
               currentConfigTM.put(chartData[0],configTM);
              if(allStatusTM.get(key)==null){
                allStatusTM.put(key,getStatus(curveData[0]));
              }
              TreeMap statusTM;
              if(currentStatusTM.get(chartData[0])==null){
                statusTM=new TreeMap();
              } else statusTM=(TreeMap) currentStatusTM.get(chartData[0]);
              statusTM.put(key,(Status)allStatusTM.get(key));
               currentStatusTM.put(chartData[0],statusTM);
              }
            }
        }
      }
         inx++;
        }
        multiPanel.setPanels(pnls);
        Iterator it5=currentChartTM.values().iterator();
        inx=0;
        for(;it5.hasNext();){
          String chartData[]=ylib.csvlinetoarray((String)it5.next());
          multiPanel.setTMData(inx, (TreeMap)currentDatumTM.get(chartData[0]), (TreeMap)currentConfigTM.get(chartData[0]), (TreeMap)currentStatusTM.get(chartData[0]), "0");
          inx++;
        }
      }
      currentChartPara=chartGroupName;
    }
    if(currentChartTM.size()>0) return;
  }
  if(currentChartTM.size()<1){
    double pnls[][]=new double[][]{{0.02,0.02,0.96,0.73}};
    multiPanel.setPanels(pnls);
    currentChartPara="default";
  }
}

public String getItemId(String name){
 String rtn="";
 switch(showType){
   case 1:
     if(name!=null && name.length()>0){
        int inx=name.lastIndexOf("("), inx2=name.lastIndexOf(")");
       rtn=name.substring(inx+1,inx2);
     }
     break;
   case 2:
    rtn=(String)nameIdMap.get(name);
    break;
 }
 return rtn;
}
public void updateList(){
  String selRec="",selSend="";
  if(receiveList.getSelectedValue()!=null) selRec=(String)receiveList.getSelectedValue();
  if(sendList.getSelectedValue()!=null) selSend=(String)sendList.getSelectedValue();
  receiveListModel.clear();
  sendListModel.clear();
  receiveListModel.addElement(allItemsName);
  sendListModel.addElement(allItemsName);
  Iterator it=nameIdMap.keySet().iterator();
  for(;it.hasNext();){
    String key=(String)it.next();

    if(key.indexOf(":")==-1){

      if(!receiveListModel.contains(key)) receiveListModel.addElement(key);
      if(!sendListModel.contains(key)) sendListModel.addElement(key);
    }
  }
  it=nameIdMap.keySet().iterator();
  for(;it.hasNext();){
    String key=(String)it.next();

    if(key.indexOf(":")>-1 && !key.startsWith("device@")){

      if(!receiveListModel.contains(key)) receiveListModel.addElement(key);
      if(!sendListModel.contains(key)) sendListModel.addElement(key);
    }
  }
  if(selRec.length()>0) receiveList.setSelectedValue(selRec, true);
  if(selSend.length()>0) sendList.setSelectedValue(selSend, true);
}

public Config getConfig(String curveId){
  String tmp[]=ylib.csvlinetoarray(curveId);
  if(allConfigTM.get(curveId)!=null) return (Config)allConfigTM.get(curveId);
  String confStr = "lin2,1431048,-65536,-1,A,1,1,1,0,0,0.0,1.0,0.0,2,45," +
            "-45,400,24,0,0,1.0,0.0,,,,Y,-24,,,," +
            "0.0,10,1,4,6,0,1.0,0.1,0.18,0.3,5,-2,0.02,0.75,0.85,0.5,0.5,68719607550,3," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,";
    String confStr2 = ",,,,,,,,,,,,," +
            ",,,,,,,,,,,,,,,,," +
            ",,,,,,,,,,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,," +
            ",,,,,,,,,,";
    String[] confA = ylib.csvlinetoarray(confStr);
    String[] confA_2 = ylib.csvlinetoarray(confStr2);
    confA[0] = "A";
    confA[1] = OneVar.add(confA[1], 16);
    confA[1] = OneVar.add(confA[1], 17);
    confA[1] = OneVar.remove(confA[1], 12);
    confA[1] = OneVar.remove(confA[1], 15);
    confA[1] = OneVar.remove(confA[1], 14);

    confA[2] = "" + Color.black.getRGB();
      confA[14] = "0.15";
      confA[15] = "-0.15";
    confA[17] = "0";
    confA[18] = "3";
    confA[19] = "0";
    confA[26] = "-2";
    confA[32] = "2";
    confA[33] = "6";
    confA[34] = "7";
    confA[42] = "0.06";
    confA[43] = "0.75";
    confA[44] = "0.63";
    confA[45] = "0.65";
    confA[46] = "0.60";
    confA[47] = "1649267441668";
    confA[47] = OneVar.add(confA[47], 52);

    confA[50] = "" + Color.red.getRGB();
    confA[51] = "" + Color.white.getRGB();
    confA[52] = "" + Color.orange.getRGB();
    confA[54] = "" + Color.orange.getRGB();
    confA[57] = "" + Color.lightGray.getRGB();
    confA[98] = "4";
    confA[108] = "3";
    confA[177] = "" + Color.red.getRGB();
    confA[178] = "" + Color.red.getRGB();
    confA[184] = "1";
    confA[185] = "1";
    confA[186] = "" + Color.gray.getRGB();
    confA[187] = "0";
    confA[188] = "11";
    confA[189] = "11";
    confA[190] = "11";
    confA_2[0] = "11";
    confA_2[1] = "2";
    confA_2[2] = "2";
    confA_2[3] = "1";
    confA_2[4] = "1";
    confA_2[9] = "2";
    confA_2[10] = "2";
    confA_2[11] = "";
    confA_2[12] = "" + (new Color(240, 248, 255)).getRGB();;
    confA_2[13] = "" + Color.white.getRGB();
    confA_2[14] = "-4";
    confA_2[51] = "0.8";
    String curveId2="";
    if(tmp.length>4) curveId2=tmp[0]+","+tmp[1]+","+tmp[2]+","+tmp[4];
    String chartData[]=null;
    if(curveTM.get(curveId2)!=null){
      String curveData[]=ylib.csvlinetoarray((String)curveTM.get(curveId2));
      confA[2] = curveData[9];
      if(chartTM.get(curveData[3])!=null){
         chartData=ylib.csvlinetoarray((String)chartTM.get(curveData[3]));
      }
    }
    if(sensors.get(curveId)!=null){
      String info[]=ylib.csvlinetoarray((String)sensors.get(curveId));
      boolean foundLimit=false;
      if(WSN.isNumeric(info[5])) {confA[20]=info[5]; confA[1]=wn.w.addOneVar(confA[1],16); }  else confA[1]=wn.w.removeOneVar(confA[1],16);
      if(WSN.isNumeric(info[6])) {confA[21]=info[6]; confA[1]=wn.w.addOneVar(confA[1],17); }  else confA[1]=wn.w.removeOneVar(confA[1],17);
      if(WSN.isNumeric(info[5]) && WSN.isNumeric(info[6])) {
        confA[14]=""+(((Double.parseDouble(info[5])+Double.parseDouble(info[6]))/2.0)+(Double.parseDouble(info[5])-Double.parseDouble(info[6]))); 
        confA[15]=""+(((Double.parseDouble(info[5])+Double.parseDouble(info[6]))/2.0)-(Double.parseDouble(info[5])-Double.parseDouble(info[6]))); 
        foundLimit=true;
      }
      if(WSN.isNumeric(info[7])) {confA[175]=info[7]; confA[47]=wn.w.addOneVar(confA[47],39);}  else confA[47]=wn.w.removeOneVar(confA[47],39);
      if(WSN.isNumeric(info[8])) {confA[176]=info[8]; confA[47]=wn.w.addOneVar(confA[47],40);}  else confA[47]=wn.w.removeOneVar(confA[47],40);
      if(WSN.isNumeric(info[7]) && WSN.isNumeric(info[8])) {
        confA[14]=""+(((Double.parseDouble(info[7])+Double.parseDouble(info[8]))/2.0)+((Double.parseDouble(info[7])-Double.parseDouble(info[8]))/2.0)+((Double.parseDouble(info[7])-Double.parseDouble(info[8]))/4.0)); 
        confA[15]=""+(((Double.parseDouble(info[7])+Double.parseDouble(info[8]))/2.0)-((Double.parseDouble(info[7])-Double.parseDouble(info[8]))/2.0)-((Double.parseDouble(info[7])-Double.parseDouble(info[8]))/4.0)); 
        foundLimit=true;
      }
      if(WSN.isNumeric(info[14])){
        confA[108]=info[14];
        confA_2[47]=info[14];
        confA[47]=wn.w.removeOneVar(confA[47],2);
        confA[47]=wn.w.removeOneVar(confA[47],52);
      }
      if(foundLimit){confA[1]=wn.w.removeOneVar(confA[1],14);} else {confA[1]=wn.w.addOneVar(confA[1],14);}

    }
    Iterator it=eventTM.values().iterator();
    for(;it.hasNext();){
      String evt[]=ylib.csvlinetoarray((String)it.next());
      int cntD=(WSN.isNumeric(evt[1])? Integer.parseInt(evt[1]):0);
      int cntA=(WSN.isNumeric(evt[2])? Integer.parseInt(evt[2]):0);
      for(int i=0;i<cntA;i++){
        String act[]=ylib.csvlinetoarray((String)actionTM.get(evt[3+cntD+i]));
        if(act[1].equalsIgnoreCase(tmp[0]) && act[39].equalsIgnoreCase(tmp[1]) && act[16].equalsIgnoreCase(tmp[2]) && act[17].equalsIgnoreCase(tmp[4]) && act[2].equalsIgnoreCase("Set data value")){
            confA[1]=(act[25].equalsIgnoreCase("Y")? wn.w.addOneVar(confA[1],13):confA[1]);
            confA[1]=(act[23].equalsIgnoreCase("Y")? wn.w.addOneVar(confA[1],57):confA[1]);
            confA[13]=act[24];
        }
      }
    }
  Config rtn= new Config(confA, confA_2);
  return rtn;
}

public Status getStatus(String curveId){
  if(allStatusTM.get(curveId)!=null) return (Status)allStatusTM.get(curveId);
  String statusStr = startTime + ",1,0,0,0,0,0,0,0,0,0.0,0.0,0.0,0.0,0.0,0,0,0,0,0,0,0,0,0,0";
  String stat[] = ylib.csvlinetoarray(statusStr);
  Status rtn = new Status(new long[]{Long.parseLong(stat[0]), Long.parseLong(stat[1]), Long.parseLong(stat[2]), Long.parseLong(stat[3]), Long.parseLong(stat[4]),
      Long.parseLong(stat[20]), Long.parseLong(stat[21]), Long.parseLong(stat[22]), Long.parseLong(stat[23]), Long.parseLong(stat[24])},
            new int[]{Integer.parseInt(stat[5]), Integer.parseInt(stat[6]), Integer.parseInt(stat[7]), Integer.parseInt(stat[8]), Integer.parseInt(stat[9])},
            new double[]{Double.parseDouble(stat[10]), Double.parseDouble(stat[11]), Double.parseDouble(stat[12]), Double.parseDouble(stat[13]), Double.parseDouble(stat[14])}, stat);
  return rtn;
}

  public void menuLabel4MouseClicked(java.awt.event.MouseEvent evt) {
    eventThread.setStatus(wn.w.getGNS(1),"",25);

  }

  private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {

  }

  String formatValue(double value, int Len, int Prec) {
    String rtn = "" + value;
    if (Prec == 0) {
      value = (double) Math.round(value);
      rtn = numberFormat.format(value);
      int rtnLength = rtn.length();
      int inx = rtn.indexOf(".");
      long value2 = (long) value;
      rtn = String.valueOf(value2);
      rtnLength = rtn.length();
      if (rtnLength > Len) {

      } else if (rtnLength < Len) {
        rtn = spc(Len - rtnLength) + rtn;
      }
    } else {
      value = (double) (Math.round(value * Math.pow(10, Prec)) / Math.pow(10, Prec));
      rtn = numberFormat.format(value);
      int rtnLength = rtn.length();
      int inx = rtn.indexOf(".");
      if (rtnLength - Prec - 1 == inx) {
      }
      else if (rtnLength - Prec - 1 > inx) {
        rtn = rtn.substring(0, inx + Prec + 1);
      } else if (rtnLength - Prec - 1 < inx) {
        rtn = rtn + zero(inx + Prec + 1 - rtnLength);
      }
      rtnLength = rtn.length();
      inx = rtn.indexOf(".");
      if (Len - Prec - 1 == inx) {
      }
      else if (Len - Prec - 1 > inx) {
        rtn = spc(Len - Prec - 1 - inx) + rtn;
      }

    }
    return rtn;
  }

  void setSerialPorts() {
    jComboBox21.addItem("");
    jComboBox22.addItem("");
    jComboBox15.addItem("");

    String portArr[] = WSNSerial.getPorts();
    for (int i = 0; i < portArr.length; i++) {
      String port = portArr[i];
      jComboBox21.addItem(port);
      jComboBox22.addItem(port);
      jComboBox15.addItem(port);
    }
    jComboBox21.setSelectedItem("");
    jComboBox22.setSelectedItem("");
    jComboBox15.setSelectedItem("");
  }

  boolean openPorts(int type) {
    int openN = 0;
    TreeMap stationsC = (TreeMap) stations.clone();
    Iterator it = stationsC.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String port = (String) stationsC.get(key);
      if (port.length() > 0) {
        if (port.indexOf(":") > -1) {
          String id=(String)nameIdMap.get(port);
          if (port.toUpperCase().indexOf("COM") != -1) {
            String contCmd = "performcommand wsn.WSN openserialport " + port + " #brate# 8 n 1 null null null null null null null null 0 0 0 0 ? ? ? 0";
            wn.w.sendToOne(contCmd, id);
            openN++;
          } else {
            String contCmd = "performcommand wsn.WSN opensocketport " + port + " 5 60 null null null null null null null 0 0 0 0 ? ? ? 0";
            wn.w.sendToOne(contCmd, id);
            openN++;
          }
        } else {
          if (port.toUpperCase().indexOf("COM") == 0 && ((DefaultComboBoxModel) jComboBox21.getModel()).getIndexOf(port) > -1) {
            String contCmd = "performcommand wsn.WSN openserialport " + port + " #brate# 8 n 1 null null null null null null null null 0 0 0 0 ? ? ? 0";
            wn.w.sendToOne(contCmd, wn.w.getGNS(1));
            openN++;
          } else {
            String contCmd = "performcommand wsn.WSN opensocketport " + port + " 5 60 null null null null null null null 0 0 0 0 ? ? ? 0";
            wn.w.sendToOne(contCmd, wn.w.getGNS(1));
            openN++;
          }
        }
      }
    }
    if (openN == 0) {
      if (type == 1) {
        JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg27"));
      } else {
        sysLog(bundle2.getString("CrInstrument.xy.msg27"));
      }
    }
    return (openN > 0);
  }

  public ImageIcon scaleImage(int WIDTH, int HEIGHT, ImageIcon ii) {
    BufferedImage bi = null;
    try {
      bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = (Graphics2D) bi.createGraphics();
      g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
      g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return new ImageIcon(bi);
  }

 public void readPorts() {
    if (wn.w.getValueString(props.getProperty("station-file")).length() > 0) {
      stationFile = wn.w.getValueString(props.getProperty("station-file"));
      stationFile=ylib.replace(stationFile, "\\", File.separator);
      stationFile=ylib.replace(stationFile, "/", File.separator);
      if(new File(stationFile).exists()){
      try {

        FileInputStream in = new FileInputStream(stationFile);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        while (true) {

          String str1 = d.readLine();

          if (str1 == null) {
            in.close();
            d.close();
            break;
          }
          if (str1.length() > 3) {
            String info[] = ylib.csvlinetoarray(str1);
            stations.put(info[0], info[1]);
          }
        }
        Iterator it = stations.keySet().iterator();
        for (; it.hasNext();) {
          String key = (String) it.next();
          String port = (String) stations.get(key);
          if (port.length() > 0) {
            ports.put(port, key);
          }
        }

      } catch (IOException e) {

        System.out.println("reading file (filename=" + stationFile + ") IOException, error message: " + e.getMessage() + "\r\n");
        e.printStackTrace();
      } catch (Exception e) {

        System.out.println("reading file (filename=" + stationFile + ") error, error message: " + e.getMessage() + "\n");
        e.printStackTrace();
      }
      int comCnt = 0;
      Iterator it = stations.values().iterator();
      for (; it.hasNext();) {
        String port = (String) it.next();
        if (port.toLowerCase().indexOf("com") == 0) {
          comCnt++;
        }
      }

      it = stations.keySet().iterator();
      int socketInx = 0, comInx = 0;
      jComboBox14.addItem("");
      jComboBox19.addItem("");
      jComboBox40.addItem("");
      for (; it.hasNext();) {
        String key = (String) it.next();
        String port = (String) stations.get(key);
        stationListModel.addElement(key);
        jComboBox14.addItem(key);
        jComboBox19.addItem(key);
        jComboBox40.addItem(key);
        if (port.toLowerCase().indexOf("com") == 0) {

          switch (comInx) {
            case 0:
              if (((DefaultComboBoxModel) jComboBox21.getModel()).getIndexOf(port) > -1) {
                jComboBox21.setSelectedItem(port);
              } else {
                if (jComboBox21.getModel().getSize() == 2 && comCnt == 1) {
                  jComboBox21.setSelectedIndex(1);
                } else {
                  jComboBox21.setSelectedItem("");
                }
              }
              jTextField22.setText(key);
              break;
            case 1:
              if (((DefaultComboBoxModel) jComboBox22.getModel()).getIndexOf(port) > -1) {
                jComboBox22.setSelectedItem(port);
              } else {
                if (((String) jComboBox21.getSelectedItem()).length() > 0 && jComboBox22.getModel().getSize() == 3 && comCnt == 2) {
                  int selInx = jComboBox21.getSelectedIndex();
                  jComboBox22.setSelectedItem(3 - selInx);
                } else {
                  jComboBox22.setSelectedItem("");
                }
              }
              jTextField23.setText(key);
              break;
            case 2:
              if (((DefaultComboBoxModel) jComboBox15.getModel()).getIndexOf(port) > -1) {
                jComboBox15.setSelectedItem(port);
              } else {
                if (((String) jComboBox21.getSelectedItem()).length() > 0 && ((String) jComboBox22.getSelectedItem()).length() > 0 && jComboBox15.getModel().getSize() == 4 && comCnt == 3) {
                  int selInx = jComboBox21.getSelectedIndex();
                  int selInx2 = jComboBox22.getSelectedIndex();
                  jComboBox15.setSelectedItem(6 - selInx - selInx2);
                } else {
                  jComboBox15.setSelectedItem("");
                }
              }
              jTextField11.setText(key);
              break;
          }
          comInx++;
        } else {
          jComboBox23.addItem(port);
          jComboBox24.addItem(port);
          jComboBox25.addItem(port);
          switch (socketInx) {
            case 0:
              jComboBox23.setSelectedItem(port);
              jTextField24.setText(key);
              break;
            case 1:
              jComboBox24.setSelectedItem(port);
              jTextField25.setText(key);
              break;
            case 2:
              jComboBox25.setSelectedItem(port);
              jTextField26.setText(key);
              break;
          }
          socketInx++;
        }

      }
      jComboBox14.setSelectedIndex(0);
      jComboBox19.setSelectedIndex(0);
      setUpSerialPortColumnFromStationTM(jTable5, jTable5.getColumnModel().getColumn(0));
      setUpSocketPortColumnFromStationTM(jTable6, jTable6.getColumnModel().getColumn(0));
      } else sysLog("station file "+stationFile+" not found.");
    } else sysLog("station file name not set.");
  }

  void saveStations() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    boolean first = true;
    Iterator it = stations.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String port = (String) stations.get(key);
      sb.append((first ? "" : "\r\n") + key + "," + port);
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(stationFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  boolean updatePorts(int type) {
    stations.clear();
    ports.clear();
    String oldStation = currentStation;
    String selected = (String)stationList.getSelectedValue();
    if (jTextField22.getText().trim().length() > 0) {
      stations.put(jTextField22.getText().trim(), (jComboBox21.getSelectedItem() != null ? (String) jComboBox21.getSelectedItem() : ""));
    }
    if (jTextField23.getText().trim().length() > 0) {
      stations.put(jTextField23.getText().trim(), (jComboBox22.getSelectedItem() != null ? (String) jComboBox22.getSelectedItem() : ""));
    }
    if (jTextField11.getText().trim().length() > 0) {
      stations.put(jTextField11.getText().trim(), (jComboBox15.getSelectedItem() != null ? (String) jComboBox15.getSelectedItem() : ""));
    }
    if (jTextField24.getText().trim().length() > 0) {
      stations.put(jTextField24.getText().trim(), (jComboBox23.getSelectedItem() != null ? (String) jComboBox23.getSelectedItem() : ""));
    }
    if (jTextField25.getText().trim().length() > 0) {
      stations.put(jTextField25.getText().trim(), (jComboBox24.getSelectedItem() != null ? (String) jComboBox24.getSelectedItem() : ""));
    }
    if (jTextField26.getText().trim().length() > 0) {
      stations.put(jTextField26.getText().trim(), (jComboBox25.getSelectedItem() != null ? (String) jComboBox25.getSelectedItem() : ""));
    }
    Iterator it = stations.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String port = (String) stations.get(key);
      if (port.length() > 0) {
        ports.put(port, key);
      }
    }

    int cnt = stationList.getModel().getSize();
    for (int i = cnt - 1; i > -1; i--) {
      String name = (String) stationList.getModel().getElementAt(i);
      if (!stations.containsKey(name)) {
        if (currentStation.equals(name)) {
          currentStation = "";
        }
        ((DefaultListModel) stationList.getModel()).remove(i);
      }
    }
    TreeMap stationsC = (TreeMap) stations.clone();
    Iterator it2 = stationsC.keySet().iterator();
    for (; it2.hasNext();) {
      String name = (String) it2.next();
      if (name != null) {
        if (stationList.getModel().getSize() == 0) {
          ((DefaultListModel) stationList.getModel()).addElement(name);
        } else if (stationList.getNextMatch(name, 0, Position.Bias.Forward) == -1) {
          ((DefaultListModel) stationList.getModel()).addElement(name);
        }
      }
    }
    if (selected != null && selected.length() > 0 && stationList.getNextMatch(selected, 0, Position.Bias.Forward) > -1) {
      stationList.setSelectedValue(selected, true);
    } else if (stationList.getModel().getSize() > 0) {
      stationList.setSelectedIndex(0);
    }
    currentStation = (String) stationList.getSelectedValue();
    if (currentStation != null && currentStation.length() > 0 && !currentStation.equals(oldStation)) {
      showStation(currentStation);
    } else if (currentStation == null || currentStation.trim().length() == 0) {
      int rcount = jTable2.getRowCount();
      for (int i = rcount - 1; i > -1; i--) {
        ((DefaultTableModel) jTable2.getModel()).removeRow(i);
      }
    }

    return true;
  }

  boolean chkProps(String key) {
    return props.getProperty(key) != null && props.getProperty(key).length() > 0 && (props.getProperty(key).equalsIgnoreCase("y") || props.getProperty(key).equalsIgnoreCase("yes"));
  }

  String getPropsString(String key) {
    return (props.getProperty(key) != null ? props.getProperty(key).trim() : "");
  }

  int getPropsInt(String key) {
    return (props.getProperty(key) != null && isNumeric(props.getProperty(key)) ? ((int) Double.parseDouble(props.getProperty(key).trim())) : 0);
  }

  long getPropsLong(String key) {
    return (props.getProperty(key) != null && isNumeric(props.getProperty(key)) ? ((long) Double.parseDouble(props.getProperty(key).trim())) : 0);
  }

  double getPropsDouble(String key) {
    return (props.getProperty(key) != null && isNumeric(props.getProperty(key)) ? Double.parseDouble(props.getProperty(key).trim()) : 0.0);
  }

  void updateUIFromProps() {
    if (chkProps("monitor-autostart")) {
      jCheckBox10.setSelected(true);
    } else {
      jCheckBox10.setSelected(false);
    }
    if (chkProps("use-engineering-unit")) {
      CBUseEngineerUnit.setSelected(true);
    } else {
      CBUseEngineerUnit.setSelected(false);
    }
    if (chkProps("show_alarmvalue")) {
      showAlarmRB.setSelected(true);
    } else {
      showAlarmRB.setSelected(false);
    }
    if (chkProps("show_remark")) {
      cbRemark.setSelected(true);
    } else {
      cbRemark.setSelected(false);
    }
    if (chkProps("auto_adjust_y")) {
      cbAutoAdjustY.setSelected(true);
    } else {
      cbAutoAdjustY.setSelected(false);
    }
    if (chkProps("show_zeroline")) {
      cbZero.setSelected(true);
    } else {
      cbZero.setSelected(false);
    }
    if (chkProps("show_insec")) {
      cbAutoAdjustY.setSelected(true);
    } else {
      cbAutoAdjustY.setSelected(false);
    }

    if (chkProps("only_receive")) {
      onlyReceiveCB.setSelected(true);

      btnStart.setEnabled(false);
    } else {
      onlyReceiveCB.setSelected(false);
      button02.setEnabled(true);

      btnStart.setEnabled(true);
    }
    jComboBox1.setSelectedItem("" + getPropsInt("monitor-interval-h"));
    jComboBox2.setSelectedItem("" + getPropsInt("monitor-interval-m"));
    jComboBox3.setSelectedItem("" + getPropsInt("monitor-interval-s"));
    jComboBox4.setSelectedItem("" + getPropsInt("record-interval-h"));
    jComboBox5.setSelectedItem("" + getPropsInt("record-interval-m"));
    jComboBox6.setSelectedItem("" + getPropsInt("record-interval-s"));

    if (getPropsString("record-directory").length() < 1) {
      props.put("record-directory", "data");
    }
    if (getPropsString("record-directory").charAt(getPropsString("record-directory").length() - 1) == File.separatorChar) {
      props.setProperty("record-directory", getPropsString("record-directory").substring(0, getPropsString("record-directory").length() - 1));
    }
    if (!(new File(getPropsString("record-directory"))).exists()) {
      (new File(getPropsString("record-directory"))).mkdirs();
    }
    jCheckBox25.setSelected(chkProps("save-calculated-data"));
    jCheckBox48.setSelected(chkProps("save-raw-data"));
    jRadioButton7.setSelected(chkProps("save-only-one-file"));
    jRadioButton8.setSelected(!chkProps("save-only-one-file"));
    jRadioButton1.setSelected(chkProps("recordwhenreceive"));
    jRadioButton6.setSelected(!chkProps("recordwhenreceive"));
    jTextField39.setText(getPropsString("maxfilesize-in-k"));
    jTextField1.setText(getPropsString("record-directory"));
    jTextField7.setText(getPropsString("record-originalfile"));
    jTextField8.setText(getPropsString("record-correctedfile"));
    jComboBox17.setSelectedItem("" + getPropsInt("record-ftp-interval-h"));
    jComboBox8.setSelectedItem("" + getPropsInt("record-ftp-interval-m"));
    jComboBox9.setSelectedItem("" + getPropsInt("record-ftp-interval-s"));
    String emto = getPropsString("email-to");
    if (emto.length() > 0) {
      String emarr[] = ylib.csvlinetoarray(emto);
      int rcount = jTable4.getRowCount();
      for (int i = 0; i < emarr.length; i++) {
        if (jTable4.getModel().getRowCount() < i + 1) {
          ((DefaultTableModel) jTable4.getModel()).addRow(new Object[jTable4.getModel().getColumnCount()]);
        }
        jTable4.getModel().setValueAt(emarr[i], i, 0);
      }
    } else {
      int rcount3 = jTable4.getRowCount();
      for (int i = 0; i < rcount3; i++) {
        jTable4.getModel().setValueAt("", i, 0);
      }
    }
    String smto = getPropsString("sms-to");
    if (smto.length() > 0) {
      String smarr[] = ylib.csvlinetoarray(smto);
      int rcount3 = jTable3.getRowCount();
      for (int i = 0; i < smarr.length; i++) {
        if (jTable3.getModel().getRowCount() < i + 1) {
          ((DefaultTableModel) jTable3.getModel()).addRow(new Object[jTable3.getModel().getColumnCount()]);
        }
        jTable3.getModel().setValueAt(smarr[i], i, 0);
      }
    } else {
      int rcount3 = jTable3.getRowCount();
      for (int i = 0; i < rcount3; i++) {
        jTable3.getModel().setValueAt("", i, 0);
      }
    }
    if (chkProps("down-alarm-email")) {
      jCheckBox1.setSelected(true);
    } else {
      jCheckBox1.setSelected(false);
    }
    if (chkProps("down-action-email")) {
      jCheckBox17.setSelected(true);
    } else {
      jCheckBox17.setSelected(false);
    }
    if (chkProps("down-alarm-sms")) {
      jCheckBox2.setSelected(true);
    } else {
      jCheckBox2.setSelected(false);
    }
    if (chkProps("down-action-sms")) {
      jCheckBox18.setSelected(true);
    } else {
      jCheckBox18.setSelected(false);
    }
    jComboBox11.setSelectedItem("" + getPropsInt("email-time-h"));
    jComboBox7.setSelectedItem("" + getPropsInt("email-time-m"));
    jComboBox12.setSelectedItem("" + getPropsInt("sms-time-h"));
    jComboBox13.setSelectedItem("" + getPropsInt("sms-time-m"));
    jComboBox10.setSelectedItem("" + getPropsString("defaultfileformat"));
    jTextField17.setText(getPropsString("ftp-ip"));
    jTextField20.setText(getPropsString("ftp-port"));
    jTextField18.setText(getPropsString("ftp-id"));
    jTextField19.setText(getPropsString("ftp-path"));
    jPasswordField6.setText(YB642D.decode(getPropsString("ftp-pw")));
    if (chkProps("ftp-passive")) {
      jCheckBox9.setSelected(true);
    } else {
      jCheckBox9.setSelected(false);
    }
    jTextField6.setText(getPropsString("email-from"));
    jPasswordField7.setText(YB642D.decode(getPropsString("email-from-pw")));
    jTextField2.setText(getPropsString("sms-from"));
    jPasswordField5.setText(YB642D.decode(getPropsString("sms-from-pw")));

    jLabel76.setText(((chkProps("recordwhenreceive")) ? getPropsInt("monitor-interval-h") + " hr " + getPropsInt("monitor-interval-m") + " min " + getPropsInt("monitor-interval-s") + " sec" : getPropsInt("record-interval-h") + " hr " + getPropsInt("record-interval-m") + " min " + getPropsInt("record-interval-s") + " sec"));

  }

  void updateProps() {
    updateProps_admin();
    updateProps_miscellaneous();
    updateProps_options();
    updateProps_else();
  }

  void updateProps_admin() {
    if (onlyReceiveCB.isSelected()) {
      props.put("only_receive", "Y");
    } else {
      props.put("only_receive", "N");
    }
    props.put("ftp-ip", jTextField17.getText());
    props.put("ftp-port", jTextField20.getText());
    props.put("ftp-id", jTextField18.getText());
    props.put("ftp-path", jTextField19.getText());
    props.put("ftp-pw", YB642E.encode(new String(jPasswordField6.getPassword())));
    if (jCheckBox9.isSelected()) {
      props.put("ftp-passive", "Y");
    } else {
      props.put("ftp-passive", "N");
    }
    props.put("email-from", jTextField6.getText());
    props.put("email-from-pw", YB642E.encode(new String(jPasswordField7.getPassword())));
    props.put("sms-from", jTextField2.getText());
    props.put("sms-from-pw", YB642E.encode(new String(jPasswordField5.getPassword())));

  }

  void updateProps_miscellaneous() {
    Vector em = new Vector();
    int rcount = jTable4.getRowCount();
    for (int i = 0; i < rcount; i++) {
      String tm = (String) jTable4.getModel().getValueAt(i, 0);
      if (tm != null && tm.length() > 0) {
        em.addElement(tm);
      }
    }
    if (em.size() > 0) {
      String arr[] = new String[em.size()];
      Object o[] = em.toArray();
      for (int i = 0; i < arr.length; i++) {
        arr[i] = (String) o[i];
      }
      props.put("email-to", ylib.arrayToCsvLine(arr));
    } else {
      props.put("email-to", "");
    }
    Vector sm = new Vector();
    int rcount3 = jTable3.getRowCount();
    for (int i = 0; i < rcount3; i++) {
      String tm = (String) jTable3.getModel().getValueAt(i, 0);
      if (tm != null && tm.length() > 0) {
        sm.addElement(tm);
      }
    }
    if (sm.size() > 0) {
      String arr[] = new String[sm.size()];
      Object o[] = sm.toArray();
      for (int i = 0; i < arr.length; i++) {
        arr[i] = (String) o[i];
      }
      props.put("sms-to", ylib.arrayToCsvLine(arr));
    } else {
      props.put("sms-to", "");
    }
    if (jCheckBox1.isSelected()) {
      props.put("up-alarm-email", "Y");
      props.put("down-alarm-email", "Y");
    } else {
      props.put("up-alarm-email", "N");
      props.put("down-alarm-email", "N");
    }
    if (jCheckBox17.isSelected()) {
      props.put("up-action-email", "Y");
      props.put("down-action-email", "Y");
    } else {
      props.put("up-action-email", "N");
      props.put("down-action-email", "N");
    }
    if (jCheckBox2.isSelected()) {
      props.put("up-alarm-sms", "Y");
      props.put("down-alarm-sms", "Y");
    } else {
      props.put("up-alarm-sms", "N");
      props.put("down-alarm-sms", "N");
    }
    if (jCheckBox18.isSelected()) {
      props.put("up-action-sms", "Y");
      props.put("down-action-sms", "Y");
    } else {
      props.put("up-action-sms", "N");
      props.put("down-action-sms", "N");
    }
    props.put("email-time-h", jComboBox11.getSelectedItem());
    props.put("email-time-m", jComboBox7.getSelectedItem());
    props.put("sms-time-h", jComboBox12.getSelectedItem());
    props.put("sms-time-m", jComboBox13.getSelectedItem());
  }

  void updateProps_options() {
    boolean atBeginIsEqual = (getPropsInt("monitor-interval-h") == getPropsInt("record-interval-h")
            && getPropsInt("monitor-interval-m") == getPropsInt("record-interval-m") && getPropsInt("monitor-interval-s") == getPropsInt("record-interval-s"));
    int oldH = getPropsInt("record-interval-h"), oldM = getPropsInt("record-interval-m"), oldS = getPropsInt("record-interval-s");
    long oldMonitorInterval=getPropsLong("monitor-interval-h")*1000L*60L*60L+getPropsLong("monitor-interval-m")*1000L*60L+getPropsLong("monitor-interval-s")*1000L;

    if (jCheckBox10.isSelected()) {
      props.put("monitor-autostart", "Y");
    } else {
      props.put("monitor-autostart", "N");
    }
    if (CBUseEngineerUnit.isSelected()) {
      props.put("use-engineering-unit", "Y");
    } else {
      props.put("use-engineering-unit", "N");
    }
    props.put("record-directory", jTextField1.getText());
    if (getPropsString("record-directory").length() < 1) {
      props.put("record-directory", "data");
      jTextField1.setText("data");
    }
    if (getPropsString("record-directory").charAt(getPropsString("record-directory").length() - 1) == File.separatorChar) {
      props.setProperty("record-directory", getPropsString("record-directory").substring(0, getPropsString("record-directory").length() - 1));
      jTextField1.setText(getPropsString("record-directory"));
    }
    if (!(new File(getPropsString("record-directory"))).exists()) {
      (new File(getPropsString("record-directory"))).mkdirs();
    }
        jCheckBox25.setSelected(chkProps("save-calculated-data"));
    jCheckBox48.setSelected(chkProps("save-raw-data"));
    jRadioButton7.setSelected(chkProps("save-only-one-file"));
    jRadioButton8.setSelected(!chkProps("save-only-one-file"));
    jRadioButton1.setSelected(chkProps("recordwhenreceive"));
    jRadioButton6.setSelected(!chkProps("recordwhenreceive"));
    jTextField39.setText(getPropsString("maxfilesize-in-k"));
    props.put("save-calculated-data", (jCheckBox25.isSelected()? "Y":"N"));
    props.put("save-raw-data", (jCheckBox48.isSelected()? "Y":"N"));
    props.put("save-only-one-file",  (jRadioButton7.isSelected()? "Y":"N"));
    props.put("recordwhenreceive",  (jRadioButton1.isSelected()? "Y":"N"));
    props.put("maxfilesize-in-k", jTextField39.getText());

    props.put("record-originalfile", jTextField7.getText());
    props.put("record-correctedfile", jTextField8.getText());
    props.put("monitor-interval-h", jComboBox1.getSelectedItem());
    props.put("monitor-interval-m", jComboBox2.getSelectedItem());
    props.put("monitor-interval-s", jComboBox3.getSelectedItem());
    props.put("record-interval-h", jComboBox4.getSelectedItem());
    props.put("record-interval-m", jComboBox5.getSelectedItem());
    props.put("record-interval-s", jComboBox6.getSelectedItem());
    props.put("defaultfileformat", jComboBox10.getSelectedItem());
    props.put("record-ftp-interval-h", jComboBox17.getSelectedItem());
    props.put("record-ftp-interval-m", jComboBox8.getSelectedItem());
    props.put("record-ftp-interval-s", jComboBox9.getSelectedItem());
    boolean newEqual = (getPropsInt("monitor-interval-h") == getPropsInt("record-interval-h")
            && getPropsInt("monitor-interval-m") == getPropsInt("record-interval-m") && getPropsInt("monitor-interval-s") == getPropsInt("record-interval-s"));
    if ((atBeginIsEqual && !newEqual) || (oldH != getPropsInt("record-interval-h") || oldM != getPropsInt("record-interval-m") || oldS != getPropsInt("record-interval-s"))) {
      saveRecordThread.setBegin();
    }
    long interval=getPropsLong("monitor-interval-h")*1000L*60L*60L+getPropsLong("monitor-interval-m")*1000L*60L+getPropsLong("monitor-interval-s")*1000L;
    if(continueMonitor && oldMonitorInterval > interval){
      if(isSleep) myThread.interrupt();
    }
  }

  void updateProps_else() {

    if (showAlarmRB.isSelected()) {
      props.put("show_alarmvalue", "Y");
    } else {
      props.put("show_alarmvalue", "N");
    }
    if (cbRemark.isSelected()) {
      props.put("show_remark", "Y");
    } else {
      props.put("show_remark", "N");
    }
    if (cbAutoAdjustY.isSelected()) {
      props.put("auto_adjust_y", "Y");
    } else {
      props.put("auto_adjust_y", "N");
    }
    if (cbZero.isSelected()) {
      props.put("show_zeroline", "Y");
    } else {
      props.put("show_zeroline", "N");
    }
    if (cbAutoAdjustY.isSelected()) {
      props.put("show_insec", "Y");
    } else {
      props.put("show_insec", "N");
    }
  }

  void updateStatus() {

  }

  public void sysLog(String msg) {
    if(wn!=null) wn.w.log(msg, logDir+File.separator+logFileHead, true);
    else System.out.println("log: "+msg);
  }
public void msgLog(String str1){
  String dDate=wn.formatter2.format(new Date()).substring(0,8);
  if(!dDate.equals(wn.dataDate)) makeDataDir();
  String file="msglog_"+dDate+".txt";
  try{
	  FileOutputStream out = new FileOutputStream (logDir+File.separator+file,true);
	  byte [] b=str1.getBytes();
	  out.write(b);
	  out.close();
      }catch(IOException e){e.printStackTrace();}
}
public void dataLog(CIDataClass dataClass){
  String dDate=wn.formatter2.format(new Date()).substring(0,8);
  if(!dDate.equals(wn.dataDate)) makeDataDir();
           String fDate=wn.formatter2.format(new Date()).substring(0,8);
           if(!fDate.equals(wn.dataDate)) makeDataDir();
           String fname=dataClass.dataSrc.replace(':','_')+"_"+fDate+".txt";
           String data=wn.formatter3.format(new Date(dataClass.time))+" "+(show16RB.isSelected()?  dataClass.data:wn.getStringData(dataClass.data, -1, -1,-1))+"\r\n";
               try{
                 FileOutputStream out;
                     out = new FileOutputStream (usedDataDir+File.separator+fname,true);
	             out.write(data.getBytes());
      	             out.close();
               } catch (IOException e){
                   e.printStackTrace();
               }
}
public void makeDataDir(){
    String fTime=wn.formatter2.format(new Date());
    wn.dataDate=fTime.substring(0,8);
   usedDataDir=dataDir+File.separator+fTime.substring(0,4)+File.separator+fTime.substring(4,6)+File.separator+fTime.substring(6,8);
    if(!(new File(usedDataDir)).exists())  (new File(usedDataDir)).mkdirs();
}
  public void log(String s, boolean logToFile) {
    s = format9.format(new Date()) + " " + s + "\r\n";

    if (!logToFile) {
      System.out.print(s);
      return;
    }
    try {
      FileOutputStream out;
      out = new FileOutputStream(logDir + File.separator + format3.format(new Date()) + "log.txt", true);
      out.write(s.toString().getBytes());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveData3(long datatime, long recordtime, int from) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("saveData3(), from " + from);
    }
    if(updateHistoryRecord) updateHistoryFile(1);
    long maxFileSize=5000000;
    if(getPropsLong("maxfilesize-in-k") >= 1000) maxFileSize=getPropsLong("maxfilesize-in-k") * 1000;
    String rawFile="",calculatedFile="";
    Iterator it=stations.keySet().iterator();
    for(;it.hasNext();){
      String station=(String)it.next();
    String head=getFileHead(station);
    boolean hasNewData=false;
    rawFile=getRawFileName(station);
    calculatedFile=getCalculatedFileName(station);
    if(rawFile.equalsIgnoreCase(calculatedFile)) rawFile=ylib.replace(rawFile, ".", "(raw).");
    if(getPropsString("record-directory").length()<1) props.setProperty("record-directory", System.getProperty("user.dir"));
    String tmp=(String)currentCalculatedRN.get(station);
    long rowNumber=0;
    if(tmp!=null && tmp.length()>0) rowNumber=Long.parseLong(tmp);
    rowNumber++;
    StringBuffer sbRaw=new StringBuffer(rowNumber+","+format4.format(new Date(datatime)));
    StringBuffer sbCalculated=new StringBuffer(rowNumber+","+format4.format(new Date(datatime)));
    Iterator it2=sensors.keySet().iterator();

    for(;it2.hasNext();){
      String key=(String)it2.next();
      String info2[]=ylib.csvlinetoarray((String)sensors.get(key));

      if(info2.length>0 && info2[0].equals(station)){

      if(info2.length>25 && info2[25].equals("1")){

        if(info2[22].length()>0 && Long.parseLong(info2[22])>0 && Long.parseLong(info2[22])>lastRecordTime){

         sbRaw.append(","+info2[17]);
         sbCalculated.append(","+info2[20]);
         hasNewData=true;
        } else {
          sbRaw.append(",");
          sbCalculated.append(",");
         }

        } else sysLog("error raw log 05, info.length<23 or is relay, info=\""+ylib.arrayToCsvLine(info2)+"\"");
    }
    }
    sbRaw.append("\r\n");
    sbCalculated.append("\r\n");
    rawFile=getPropsString("record-directory")+File.separator+rawFile;
    String data=sbRaw.toString();
    if(hasNewData && chkProps("save-raw-data")){
    if(!(new File(rawFile)).exists()){
          data=head+"\r\n"+data;
    }
    try{
        FileOutputStream out;
        out=new FileOutputStream(rawFile,true);
        out.write(data.getBytes());
        out.close();
        currentCalculatedRN.put(station, ""+rowNumber);
    }catch(IOException e){
        e.printStackTrace();
    }
    }

     calculatedFile=getPropsString("record-directory")+File.separator+calculatedFile;
    data=sbCalculated.toString();
    if(hasNewData && chkProps("save-calculated-data")){
    if(!(new File(calculatedFile)).exists()){
          data=head+"\r\n"+data;
    }
    try{
        FileOutputStream out;
        out=new FileOutputStream(calculatedFile,true);
        out.write(data.getBytes());
        out.close();
        currentCalculatedRN.put(station, ""+rowNumber);
    }catch(IOException e){
        e.printStackTrace();
    }
    }
    if(n120>600){
      File f=new File(rawFile);
      if(f.length()>maxFileSize){
        updateHistoryFile(10);
      }
     }
    }
    lastRecordTime=recordtime;
    if(n120>600) n120=0;
  }
  String getRawFileName(String station){
    String rawFile="";
    rawFile=getPropsString("record-originalfile");
    int inx=rawFile.indexOf(".");
    if(inx>-1) rawFile=rawFile.substring(0,inx);
    rawFile=rawFile+(rawFile.length()>0? "-":"")+ylib.replace(station," ","")+(chkProps("save-only-one-file")? "":"-"+format3.format(new Date()))+"."+getFileFormat();
      return rawFile;
  }
  String getCalculatedFileName(String station){
    String calculatedFile="";
    calculatedFile=getPropsString("record-correctedfile");
    int inx=calculatedFile.indexOf(".");
    if(inx>-1) calculatedFile=calculatedFile.substring(0,inx);
    calculatedFile=calculatedFile+(calculatedFile.length()>0? "-":"")+ylib.replace(station," ","")+(chkProps("save-only-one-file")? "":"-"+format3.format(new Date()))+"."+getFileFormat();
    return calculatedFile;
  }
String getFileHead(String station){
    String firstLine="No.,Time";
    String secondLine=",";
    Iterator it=sensors.keySet().iterator();
    String lastKey="";
    for(;it.hasNext();){
        String key=(String)it.next();
        String[] info=ylib.csvlinetoarray((String)sensors.get(key));
        if(info[0].equalsIgnoreCase(station)){
            String key2=info[1]+"-"+info[2]+"-"+info[3];
            if(!key2.equalsIgnoreCase(lastKey)){
                firstLine=firstLine+","+key2;
                lastKey=key2;
            } else firstLine=firstLine+",";
            secondLine=secondLine+","+info[4]+(info.length>9&& info[9].length()>0? "("+info[9]+")":"");
        }
    }
    String head=firstLine+"\r\n"+secondLine;
    return head;
}
  String getFileFormat() {
    if (getPropsString("defaultfileformat").toLowerCase().indexOf("dat") > -1) {
      return "dat";
    }
    if (getPropsString("defaultfileformat").toLowerCase().indexOf("csv") > -1) {
      return "csv";
    }
    return "txt";
  }

  void getCurrentRN() {
    Iterator it = stations.keySet().iterator();
    for (; it.hasNext();) {
      String station = (String) it.next();
      String rawFile = getRawFileName(station);
      String calculatedFile = getCalculatedFileName(station);
      if (getPropsString("record-directory").length() < 1) {
        props.setProperty("record-directory", System.getProperty("user.dir"));
      }
      long rowNumber = 0;
      rawFile = getPropsString("record-directory") + File.separator + rawFile;
      if (new File(rawFile).exists()) {
        try {
          FileInputStream in = new FileInputStream(rawFile);
          BufferedReader d = new BufferedReader(new InputStreamReader(in));
          while (true) {
            String str1 = d.readLine();
            if (str1 == null) {
              in.close();
              d.close();
              break;
            }
            if (str1.length() > 11) {
              String info[] = ylib.csvlinetoarray(str1);
              if (wn.isNumeric(info[1])) {
                rowNumber = Long.parseLong(info[0]);
              }
            }
          }

        } catch (IOException e) {

          sysLog("reading file (filename=" + rawFile + ") IOException, error message: " + e.getMessage() + "\r\n");
          e.printStackTrace();
        } catch (Exception e) {

          sysLog("reading file (filename=" + rawFile + ") error, error message: " + e.getMessage() + "\n");
          e.printStackTrace();
        }
      }
      currentCalculatedRN.put(station, "" + rowNumber);

      long rowNumber2 = 0;
      calculatedFile = getPropsString("record-directory") + File.separator + calculatedFile;
      if (new File(calculatedFile).exists()) {
        try {
          FileInputStream in = new FileInputStream(calculatedFile);
          BufferedReader d = new BufferedReader(new InputStreamReader(in));
          while (true) {
            String str1 = d.readLine();
            if (str1 == null) {
              in.close();
              d.close();
              break;
            }
            if (str1.length() > 11) {
              String info[] = ylib.csvlinetoarray(str1);
              if (wn.isNumeric(info[1])) {
                rowNumber2 = Long.parseLong(info[0]);
              }
            }
          }

        } catch (IOException e) {

          sysLog("reading file (filename=" + calculatedFile + ") IOException, error message: " + e.getMessage() + "\r\n");
          e.printStackTrace();
        } catch (Exception e) {

          sysLog("reading file (filename=" + calculatedFile + ") error, error message: " + e.getMessage() + "\n");
          e.printStackTrace();
        }
      }
      if(rowNumber2>rowNumber) rowNumber=rowNumber2;
      currentCalculatedRN.put(station, "" + rowNumber);

    }
  }

  void updateTitle() {

    String title="cr-Instrument " + version;
    if(currentUI.get("frame")!=null){
        String info[]=ylib.csvlinetoarray((String)currentUI.get("frame"));
        if(info.length>1 && info[1].trim().length()>0) title=info[1].trim();
    }
    if(wn!=null )setTitle(title + "          in Group: " + wn.w.getGNS(11) + (wn.w.getGNS(38).length() > 0 ? "      connect to: " + wn.w.getGNS(8) : "") + (wn.gs != null ? "      number of client: " + wn.gs.connectionCount() : "") + (hasNewVersion ? "      (" + bundle2.getString("CrInstrument.xy.msg1") + ")" : ""));
  }

  void handleWaitDevice_c() {
    if (waitDeviceAddr_c.length() > 0) {
      if (waitDeviceActionCount_c > 3) {
        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("waitDeviceActionCount_c>3, waitDeviceAddr_c=" + waitDeviceAddr_c);
        }
        String toId[] = getToId_c(waitDeviceAddr_c, 2);
        String nextToId[] = getNext_c(toId[0], toId[1], 2);

        String key = (String) deviceToKeys.get(waitDeviceAddr_c);
        String info2[];
        long time = System.currentTimeMillis();
        if (key != null) {
          info2 = ylib.csvlinetoarray((String) sensors.get(key));
          info2[24] = String.valueOf(time);
          sensors.put(key, ylib.arrayToCsvLine(info2));
        }
        waitDeviceAddr_c = "";

        if (nextToId != null) {
          switch (waitDeviceAction_c) {
            case 1:
              if (nextToId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  String cmd = "CC FF 09 07 " + nextToId[4] + " FF CC";

                  if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                    sysLog(wn.getPort(nextToId[0]) + "-out1,cmd=" + cmd);
                  }
                  cmd = wn.w.e642(cmd);
                  cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
                  wn.w.sendToOne(cmd, wn.w.getGNS(1));
                  lastIssueCmdTime_c = System.currentTimeMillis();
                  waitDeviceAddr_c = nextToId[4];
                  waitDeviceTime_c = System.currentTimeMillis();
                  waitDeviceAction_c = 1;
                  waitDeviceActionCount_c = 0;
                }
              }
              break;
            case 2:
              if (nextToId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  String cmd = "CC FF 13 67 " + nextToId[4] + " " + nextToId[3] + " 01 16 FF CC";

                  if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                    sysLog(wn.getPort(nextToId[0]) + "-out1,cmd=" + cmd);
                  }
                  cmd = wn.w.e642(cmd);
                  cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
                  wn.w.sendToOne(cmd, wn.w.getGNS(1));
                  lastIssueCmdTime_c = System.currentTimeMillis();
                  waitDeviceAddr_c = nextToId[4];
                  waitDeviceTime_c = System.currentTimeMillis();
                  waitDeviceAction_c = 2;
                  waitDeviceActionCount_c = 0;
                }
              }
              break;
            case 3:
              if (nextToId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {

                  String cmd = "CC FF 13 67 " + nextToId[4] + " " + nextToId[3] + " 01 16 FF CC";

                  if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                    sysLog(wn.getPort(nextToId[0]) + "-out1,cmd=" + cmd);
                  }
                  cmd = wn.w.e642(cmd);
                  cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
                  wn.w.sendToOne(cmd, wn.w.getGNS(1));
                  lastIssueCmdTime_c = System.currentTimeMillis();
                  waitDeviceAddr_c = nextToId[4];
                  waitDeviceTime_c = System.currentTimeMillis();
                  waitDeviceAction_c = 3;
                  waitDeviceActionCount_c = 0;
                }
              }
              break;
          }
        }
      }
      long now = System.currentTimeMillis();
      if (now - waitDeviceTime_c > 1000L) {
        String toId[] = getToId_c(waitDeviceAddr_c, 2);
        if (toId != null) {
          switch (waitDeviceAction_c) {
            case 1:
              if (toId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  String cmd = "CC FF 09 07 " + toId[4] + " FF CC";

                  if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                    sysLog(wn.getPort(toId[0]) + "-out1,cmd=" + cmd);
                  }
                  cmd = wn.w.e642(cmd);
                  cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
                  wn.w.sendToOne(cmd, wn.w.getGNS(1));
                  lastIssueCmdTime_c = System.currentTimeMillis();
                }
              }
              break;
            case 2:
              if (toId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  String cmd = "CC FF 13 67 " + toId[4] + " " + toId[3] + " 01 16 FF CC";

                  if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                    sysLog(wn.getPort(toId[0]) + "-out1,cmd=" + cmd);
                  }
                  cmd = wn.w.e642(cmd);
                  cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
                  wn.w.sendToOne(cmd, wn.w.getGNS(1));
                  lastIssueCmdTime_c = System.currentTimeMillis();
                }
              }
              break;
            case 3:
              if (toId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  String cmd = "CC FF 13 67 " + toId[4] + " " + toId[3] + " 01 17 FF CC";

                  if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                    sysLog(wn.getPort(toId[0]) + "-out1,cmd=" + cmd);
                  }
                  cmd = wn.w.e642(cmd);
                  cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
                  wn.w.sendToOne(cmd, wn.w.getGNS(1));
                  lastIssueCmdTime_c = System.currentTimeMillis();
                }
              }
              break;
          }
        }
      }
      waitDeviceActionCount_c++;
    }
  }

  void handleWaitDevice_m() {
    if (waitKey_m.length() > 0) {
      if (waitKeyActionCount_m > 3) {
        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("waitKeyActionCount_m>3, waitKey_m=" + waitKey_m);
        }
        String toId[] = getToId_m(waitKey_m);
        String nextToId[] = getNext_c(toId[0], toId[1], 2);

        String info2[];
        long time = System.currentTimeMillis();
        String inf = (String) sensors.get(waitKey_m);
        if (inf != null) {
          info2 = ylib.csvlinetoarray(inf);
          info2[24] = String.valueOf(time);
          sensors.put(waitKey_m, ylib.arrayToCsvLine(info2));
        }
        waitKey_m = "";

        if (nextToId != null) {
          switch (waitKeyAction_m) {
            case 1:
              break;
            case 2:
              if (nextToId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  putCmd_m(nextToId);
                  waitKey_m = nextToId[0] + "," + nextToId[1];
                  waitKeyTime_m = System.currentTimeMillis();
                  waitKeyAction_m = 2;
                  waitKeyActionCount_m = 0;
                }
              }
              break;
            case 3:
              break;
          }
        }
      }
      long now = System.currentTimeMillis();
      if (now - waitKeyTime_m > 1000L) {
        String toId[] = getToId_m(waitKey_m);
        if (toId != null) {
          switch (waitKeyAction_m) {
            case 1:
              break;
            case 2:
              if (toId[2].indexOf(":") > -1) {
              } else {
                if (!onlyReceiveCB.isSelected()) {
                  putCmd_m(toId);
                }
              }
              break;
            case 3:
              break;
          }
        }
      }
      waitKeyActionCount_m++;
    }
  }

  public void readSensors() {
    if (wn.w.getValueString(props.getProperty("sensor-file")).length() > 0) {
      sensorFile = wn.w.getValueString(props.getProperty("sensor-file"));
      sensorFile = sensorFile.replace('\\', File.separatorChar);
      sensorFile=sensorFile.replace('/',File.separatorChar);
      if(new File(sensorFile).exists()){
    try {

      FileInputStream in = new FileInputStream(sensorFile);
      BufferedReader d = new BufferedReader(new InputStreamReader(in));
      sensors.clear();
      while (true) {

        String str1 = d.readLine();

        if (str1 == null) {
          in.close();
          d.close();
          break;
        }
        if (str1.length() > 3) {
          String info[] = ylib.csvlinetoarray(str1);

          info[11] = "0.0";
          info[12] = "0.0";
          info[13] = "0.0";
          info[17] = "0.0";
          info[18] = "0.0";
          info[19] = "0.0";
          info[20] = "0.0";
          info[21] = "";
          info[22] = "0";
          if (chkProps("only_receive")) {
            info[23] = "";
          }
          info[24] = "0";

          sensors.put(info[0] + "," + info[1]+","+info[2]+","+info[3]+","+info[4], ylib.arrayToCsvLine(info));
        }
      }
    } catch (IOException e) {

      System.out.println("reading file (filename=" + sensorFile + ") IOException, error message: " + e.getMessage() + "\r\n");
      e.printStackTrace();
    } catch (Exception e) {

      System.out.println("reading file (filename=" + sensorFile + ") error, error message: " + e.getMessage() + "\n");
      e.printStackTrace();
    }
    if(sensors.size()==1){
        Iterator it=sensors.keySet().iterator();
        String key=(String)it.next();
        String info[]=ylib.csvlinetoarray((String)sensors.get(key));
        if(info.length>23) {
          info[23]="";
          sensors.put(key, ylib.arrayToCsvLine(info));
        }
      }
    updateSensorTable();
      } else sysLog("sensor file "+sensorFile+" not found.");
    } else sysLog("sensor file undefined.");
  }

  void updateSensorTable() {
    rowToRandomID.clear();
    Iterator it = sensors.keySet().iterator();
    int inx = 0;
    int rcount = jTable1.getRowCount();
    for (int i = rcount - 1; i > -1; i--) {
      ((DefaultTableModel) jTable1.getModel()).removeRow(i);
    }
    for (; it.hasNext();) {
      String key = (String) it.next();
      String info[] = ylib.csvlinetoarray(key);
      String name = info[0];
      String port = (String) stations.get(name);
      String id = info[1];
      String info2[] = ylib.csvlinetoarray((String) sensors.get(key));
      if (jTable1.getModel().getRowCount() < inx + 1) {
        ((DefaultTableModel) jTable1.getModel()).addRow(new Object[jTable1.getModel().getColumnCount()]);
      }
      jTable1.getModel().setValueAt(name, inx, 0);
      jTable1.getModel().setValueAt(info2[1], inx, 1);
      jTable1.getModel().setValueAt(info2[2], inx, 2);
      jTable1.getModel().setValueAt(info2[3], inx, 3);

      jTable1.getModel().setValueAt(info2[4], inx, 4);

      jTable1.getModel().setValueAt((info2[14].trim().length()>0? info2[14]:"0"), inx, 5);
      jTable1.getModel().setValueAt(info2[9], inx, 6);
      jTable1.getModel().setValueAt(info2[10], inx, 7);
      jTable1.getModel().setValueAt(info2[15], inx, 8);
      jTable1.getModel().setValueAt(info2[16], inx, 9);
      jTable1.getModel().setValueAt(info2[26], inx, 10);
      jTable1.getModel().setValueAt((info2[25].equals("1")? "No":"Yes"), inx, 11);
      jTable1.getModel().setValueAt(info2[5], inx, 12);
      jTable1.getModel().setValueAt(info2[6], inx, 13);
      jTable1.getModel().setValueAt(info2[7], inx, 14);
      jTable1.getModel().setValueAt(info2[8], inx, 15);

      info2[21] = "" + Math.round(Math.random() * 100000000.0D);
      rowToRandomID.put("" + inx, info2[21]);

      sensors.put(key, ylib.arrayToCsvLine(info2));
      inx++;
    }
    changeSensor = false;
  }

  void saveSensors() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = sensors.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(sensorFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void updateSensors() {
    TreeMap tm = new TreeMap();
    int inx = 0;
    int rcount = jTable1.getRowCount();
    for (int i = 0; i < rcount; i++) {
      String data[] = new String[jTable1.getModel().getColumnCount()];

      data[0]=(String)jTable1.getModel().getValueAt(i,0);
      data[1] = (String) jTable1.getModel().getValueAt(i, 1);
      data[2] = (String) jTable1.getModel().getValueAt(i, 2);
      data[3]=(String)jTable1.getModel().getValueAt(i,3);
      data[4] = (String) jTable1.getModel().getValueAt(i, 4);
      data[14] = (String) jTable1.getModel().getValueAt(i, 5);
      data[9] = (String) jTable1.getModel().getValueAt(i, 6);
      data[10] = (String) jTable1.getModel().getValueAt(i, 7);
      data[15] = (String) jTable1.getModel().getValueAt(i, 8);
      data[16] = (String) jTable1.getModel().getValueAt(i, 9);
      data[26] = (String) jTable1.getModel().getValueAt(i, 10);
      String tmp=(String) jTable1.getModel().getValueAt(i, 11);
      data[25] =  (tmp.equalsIgnoreCase("Yes") || tmp.equalsIgnoreCase("Y")? "0":"1");
      data[5] = (String) jTable1.getModel().getValueAt(i, 12);
      data[6] = (String) jTable1.getModel().getValueAt(i, 13);
      data[7] = (String) jTable1.getModel().getValueAt(i, 14);
      data[8] = (String) jTable1.getModel().getValueAt(i, 15);
      if (data[0] != null && data[1] != null && data[2] != null && data[3] != null && data[4] != null) {
        tm.put(data[0] + "," + data[1]+","+data[2]+","+data[3]+","+data[4], ylib.arrayToCsvLine(data));
      }
    }
    sensors = tm;
  }

  public void onExit(int type) {

    if(wn!=null) eventThread.setStatus(wn.w.getGNS(1),"",51);
    updateProps_else();
    saveProps();

    saveSensors();
    saveStatus();
    saveEvents();
    saveConditions();
    saveActions();
    saveCharts();
    saveCurves();
    saveUI();
    saveSmsSp();
    saveEmailSp();
    sysLog("version=" + version + ",versionTime=" + versionTime + ", starttime=" + format4.format(new Date(startTime)) + ", stoptime=" + format4.format(new Date())
            + ", executetime=" + ((System.currentTimeMillis() - startTime) / 1000L) + " secs,\r\nonly_receive=" + chkProps("only_receive")
            + ", dynamic_id=" + chkProps("dynamic_id")
            + ", record_when_receive=" + chkProps("recordwhenreceive") + ",module_type=" + getPropsString("moduletype") + ",getDataAfteDeviceFeedBack=" + getDataAfteDeviceFeedBack + "\r\nsumCycleTime=" + (sumCycleTime / 1000L)
            + " secs, cycyleCount=" + cycleCnt + ", averageCycleTime=" + averageCycleTime + " ms, largestCycleTime="
            + largestCycleTime + " ms,\r\nsmallestCycleTime=" + smallestCycleTime
            + " ms,\r\n cycleStartTime=" + (cycleStartTime == 0 ? "0" : format4.format(new Date(cycleStartTime)))
            + ", cycleStopTime=" + (cycleStopTime == 0 ? "0" : format4.format(new Date(cycleStopTime)))
            + ",lastIssueCmdTime_c=" + (lastIssueCmdTime_c == 0 ? "0" : format4.format(new Date(lastIssueCmdTime_c)))
            + ",lastIssueCmdTime_m=" + (lastIssueCmdTime_m == 0 ? "0" : format4.format(new Date(lastIssueCmdTime_m)))
            + ",\r\nlastReceiveTime=" + (lastReceiveTime == 0 ? "0" : format4.format(new Date(lastReceiveTime)))
            + ",lastDataTime=" + (lastDataTime == 0 ? "0" : format4.format(new Date(lastDataTime)))
            + ", lastRecordTime=" + (lastRecordTime == 0 ? "0" : format4.format(new Date(lastRecordTime)))+", exit code="+type);
  }
  void restart(){
    onExit(110);

                           if(wn.w.gs!=null){
                             for(Enumeration k= wn.w.gs.groups.keys(); k.hasMoreElements();){
                               String g2= (String)k.nextElement();
                               GroupInfo gi=(GroupInfo) wn.w.gs.groups.get(g2); 
                               if(gi.recordContent==true){
                                  wn.w.gs.nap.saveRecord(g2,gi.starttime,gi.record.toString());
                               }
                             }
                             wn.w.gs.stop();
                           }
       if(System.getProperty("os.name").toLowerCase().indexOf("win")>-1 && (new File("temp_update"+File.separator+"deploy")).exists() && !(new File("temp_update"+File.separator+"z")).exists()){
           new Thread(){
                              public void run(){
                                 String cmd[] = {"cmd.exe","/C",(wn.w.chkValue(props.getProperty("ci-demo"))? "start demo.bat":"start run.bat")};
                                 Runtime rt = Runtime.getRuntime();
                                 try{
                                   Process proc = rt.exec(cmd);
                                 } catch(IOException e){e.printStackTrace();}
                               }
                           }.start();
       } else if((System.getProperty("os.name").indexOf( "nix") >=0 || System.getProperty("os.name").indexOf( "nux") >=0) && (new File("temp_update"+File.separator+"deploy")).exists() && !(new File("temp_update"+File.separator+"z")).exists()){
           new Thread(){
                              public void run(){
                                 Runtime rt = Runtime.getRuntime();
                                 try{

                                   Process proc = rt.exec((wn.w.chkValue(props.getProperty("ci-demo"))? "sh demo.sh 0 &":"sh run.sh 0 &"));

                                 } catch(IOException e){e.printStackTrace();}
                               }
                           }.start();
       }else{
         String xcommand2="java -classpath "+System.getProperty("java.class.path")+" Infinity "+wn.w.replace(wn.w.getSx(3),","," ");

                           restartStr=xcommand2;
           new Thread(){
                 public void run(){
              Runtime rt = Runtime.getRuntime();
              String os = System.getProperty("os.name").toLowerCase();
              try{
                if(os.indexOf( "win" ) >= 0){
                  String cmd[] = {"cmd.exe","/C","start "+restartStr};
                   Process proc = rt.exec(cmd);
                }else if(os.indexOf( "mac" ) >= 0){
                   rt.exec( "open" + restartStr);
                } else if(os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0){

                  Process proc = rt.exec(restartStr+" &");
                }
              } catch(IOException e){e.printStackTrace();}

                           }
                           }.start();
       }
                           try{
                             Thread.sleep(2*1000);
                           } catch(InterruptedException e){}
                           wn.w.ap.onExit(111);
                           System.exit(0);
}

  public boolean loadClass(String className,int type)  {
    String classDir="apps/cr-wsn/lib/classes";
    if(className.length()>6 && className.indexOf(".class")==className.length()-6) className=className.substring(0,className.indexOf(".class"));
    File f3=new File(wn.w.replace(classDir,"/",File.separator));
    boolean found=false;
    try{
     wn.w.serverURLs = new URL[]{new URL("file:"+classDir)};
     wn.w.cl = new URLClassLoader(wn.w.serverURLs);
     File classFile=new File(classDir+File.separator+className+".class");
     long lastFileTime=0;
     if(aTime.get(className)!=null){
        lastFileTime=Long.parseLong((String)aTime.get(className));
      }
    if(f3.exists()){
     if(classFile.exists()){
      long fileTime=classFile.lastModified();
      if(aTime.get(className)==null || fileTime > lastFileTime){
         jClasses.put(className,wn.w.cl.loadClass(className).newInstance());
         aTime.put(className,String.valueOf(fileTime));
         try{
           if((type==1 && !((CITransferClass)jClasses.get(className) instanceof CITransferClass)) || 
                   (type==2 && !((CIAction)jClasses.get(className) instanceof CIAction)) || 
                   (type == 3 && !((CIChkDataClass)jClasses.get(className) instanceof CIChkDataClass)) || 
                   (type == 4 && !((CIDataGenerator) jClasses.get(className) instanceof CIDataGenerator)) || 
                   (type == 5 && !((WSNSocketDataHandler) jClasses.get(className) instanceof WSNSocketDataHandler))){
             jClasses.remove(className);
             System.out.println("Error1: class file "+classFile.getAbsolutePath()+" not a "+(type==1? "CITransferClass":(type==2? "CIAction":(type==3? "CIChkDataClass":(type==4? "CIDataGenerator":"UnknownType"))))+" class."); 
           } else {
               found=true;
              if(type == 3) {

              }
           }
         } catch(ClassCastException e){
             jClasses.remove(className);
             System.out.println("Error2: class file "+classFile.getAbsolutePath()+" can not be cast to a "+(type==1? "CITransferClass":(type==2? "CIAction":(type==3? "CIChkDataClass":(type==4? "CIDataGenerator":"UnknownType"))))+" class."); 
         }
       } else found=true;
     }
     else {
      } 
    }  
    if(!found){
      if(jClasses.get(className)==null){
             jClasses.put(className,wn.w.cl.loadClass(className).newInstance());
           try{
             aTime.put(className,String.valueOf(System.currentTimeMillis()));
              if((type==1 && !((WSNDataTranslator)jClasses.get(className) instanceof WSNDataTranslator)) || (type==2 && !((CIAction)jClasses.get(className) instanceof CIAction)) || (type==4 && !((CIDataGenerator)jClasses.get(className) instanceof CIDataGenerator)) || (type==5 && !((WSNSocketDataHandler)jClasses.get(className) instanceof WSNSocketDataHandler))){
                jClasses.remove(className);
                System.out.println("Error5: class file "+className+" in jar file not a "+(type==1? "WSNDataTranslator":(type==2? "CIAction":(type==3? "WSNApplication":(type==4? "CIDataGenerator":(type==5? "WSNSocketDataHandler":"UnknownType")))))+" class."); return false;
             }
            }catch(ClassCastException e){
               jClasses.remove(className);
               System.out.println("Error6: class file "+className+" in jar file can not be cast to a "+(type==1? "WSNDataTranslator":(type==2? "CIAction":(type==3? "WSNApplication":(type==4? "CIDataGenerator":(type==5? "WSNSocketDataHandler":"UnknownType")))))+" class."); return false;
            }
         }
    }
      }  catch (ClassNotFoundException e) {
    	    if(jClasses.get(className)!=null) jClasses.remove(className);
            if(wn.w.w_monitor) System.out.println("Error8: "+className+".class not found in class directory or jar files.");

            return false;
      }  catch (Exception e) {
    	    if(jClasses.get(className)!=null) jClasses.remove(className);
            e.printStackTrace();
            return false;
        }
    return true;
  }

  public boolean runInBackground() {
    return false;
  }

  public void run() {
    long now = System.currentTimeMillis(), lastTime = 0L, nextTime = 0L;
    while (true) {
      isSleep = false;
      now = System.currentTimeMillis();
      if (lastTime == 0) {
        lastTime = now;
      }
      long interval = getPropsLong("monitor-interval-h") * 1000L * 60L * 60L + getPropsLong("monitor-interval-m") * 1000L * 60L + getPropsLong("monitor-interval-s") * 1000L;
      if (interval < 1000L) {
        interval = 1000L;
      }

      if (continueMonitor) {
        

      }
      try {
        isSleep = true;
        nextTime = lastTime + interval;
        long waitTime = 0L;
        if (continueMonitor) {
          waitTime = nextTime - now;
        } else {
          waitTime = 1000L * 60L * 60L * 24L * 365L;
        }              
        if (waitTime > 0) {
          Thread.sleep(waitTime);
        }
        lastTime = nextTime;
      } catch (InterruptedException e) {
        lastTime = 0L;
      }
    }
  }

  void setBegin() {
    if (isSleep) {
      myThread.interrupt();
    }
  }

  public String[] getNext_old(String currentStation, String currentId) {
    boolean found = false;
    if (currentStation.length() < 1) {
      found = true;
    }
    String lastKey = (String) sensors.lastKey();
    String lastInfo[] = ylib.csvlinetoarray(lastKey);
    if (currentStation.equals(lastInfo[0]) && currentId.equals(lastInfo[1])) {
      found = true;
    }
    TreeMap sensorsC = (TreeMap) sensors.clone();
    Iterator it = sensorsC.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String info[] = ylib.csvlinetoarray(key);
      String port = (String) stations.get(info[0]);
      String coorAddr = (String) portToCoors.get(port);
      String deviceAddr = "";
      if (found) {
        if (coorAddr != null && coorDevices.get(coorAddr) != null) {
          TreeMap tm = (TreeMap) coorDevices.get(coorAddr);

          if (tm.get(info[1]) != null) {
            deviceAddr = (String) tm.get(info[1]);
            return new String[]{info[0], info[1], port, coorAddr, deviceAddr};
          }
        }
      } else {
        if (currentStation.equals(info[0]) && currentId.equals(info[1])) {
          found = true;
        }        
      }
    }
    return null;
  }
  

  public String[] getNext_c(String currentStation, String currentId, int moduletype) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("getNext_c(" + currentStation + "," + currentId + "," + moduletype + "), sensors.size=" + sensors.size() + ",stations.size=" + stations.size() + ",portToCoors.size=" + portToCoors.size() + ",coorDevices.size=" + coorDevices.size());
    }
    boolean found = false;
    if (currentStation.length() < 1) {
      found = true;
    }
    TreeMap sensorsC = (TreeMap) sensors.clone();
    Iterator it = sensorsC.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String info[] = ylib.csvlinetoarray((String) sensors.get(key));
      String port = (String) stations.get(info[0]);
      if (port != null) {

        String coorAddr = (portToCoors.get(port) == null ? null : (String) portToCoors.get(port));
        String deviceAddr = "";

        if (currentStation.equals(info[0]) && currentId.equals(info[1])) {
          found = true;
        }        
        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("key=" + key + ",info.length=" + info.length + ",info[0]=" + info[0] + ",info[1]=" + info[1] + ",info[22]=" + (info.length > 22 ? info[22] : "")
                  + ",info[23]=" + (info.length > 23 ? info[23] : "") + ",info[24]=" + (info.length > 24 ? info[24] : "") + ",info[25]=" + (info.length > 25 ? info[25] : "") + ",port=" + port + ",found=" + found
                  + ",coorAddr=" + coorAddr + ((coorAddr != null && coorDevices != null) ? ",coorDevices.get(\"" + coorAddr + "\")!=null)? " + (coorDevices.get(coorAddr) != null) : ""));
        }
        if (found && coorAddr != null && coorDevices.get(coorAddr) != null) {
          if (currentStation.equals("") || (!(currentStation.equals(info[0]) && currentId.equals(info[1])))) {
            TreeMap tm = (TreeMap) coorDevices.get(coorAddr);
            if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
              sysLog("coorAddr=" + coorAddr + ",deviceAddr=tm.get(\"" + info[1] + "\")=" + (String) tm.get(info[1]));
            }
            if (tm.get(info[1]) != null && (moduletype == 1 || (info.length > 25 && info[25].equals("1")))) {

              deviceAddr = (String) tm.get(info[1]);
              if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                sysLog("next device addr=" + deviceAddr);
              }
              return new String[]{info[0], info[1], port, coorAddr, deviceAddr};

            }
          }
        }
      }
    }
    return null;
  }

  public String[] getToId_c(String deviceAddr, int moduletype) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("getToId_c(" + deviceAddr + "," + moduletype + "), sensors.size=" + sensors.size() + ",stations.size=" + stations.size() + ",portToCoors.size=" + portToCoors.size() + ",coorDevices.size=" + coorDevices.size());
    }      
    TreeMap sensorsC = (TreeMap) sensors.clone();
    Iterator it = sensorsC.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String info[] = ylib.csvlinetoarray((String) sensors.get(key));
      String port = (String) stations.get(info[0]);
      if (port != null) {

        String coorAddr = (portToCoors.get(port) == null ? null : (String) portToCoors.get(port));

        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("key=" + key + ",info.length=" + info.length + ",info[0]=" + info[0] + ",info[1]=" + info[1] + ",info[22]=" + (info.length > 22 ? info[22] : "")
                  + ",info[23]=" + (info.length > 23 ? info[23] : "") + ",info[24]=" + (info.length > 24 ? info[24] : "") + ",info[25]=" + (info.length > 25 ? info[25] : "") + ",port=" + port
                  + ",coorAddr=" + coorAddr + ((coorAddr != null && coorDevices != null) ? ",coorDevices.get(\"" + coorAddr + "\")!=null)? " + (coorDevices.get(coorAddr) != null) : ""));
        }
        if (coorAddr != null && coorDevices.get(coorAddr) != null) {
          TreeMap tm = (TreeMap) coorDevices.get(coorAddr);
          if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
            sysLog("coorAddr=" + coorAddr + ",deviceAddr=tm.get(\"" + info[1] + "\")=" + (String) tm.get(info[1]));
          }
          if (tm.get(info[1]) != null && deviceAddr.equals((String) tm.get(info[1])) && (moduletype == 1 || (info.length > 25 && info[25].equals("1")))) {

            deviceAddr = (String) tm.get(info[1]);
            if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
              sysLog("result=" + info[0] + "-" + info[1]);
            }
            return new String[]{info[0], info[1], port, coorAddr, deviceAddr};

          }
        }
      }
    }
    return null;
  }
  

  public String[] getNext_m(String currentStation, String currentId, int moduletype) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("getNext_m(" + currentStation + "," + currentId + "," + moduletype + "), sensors.size=" + sensors.size() + ",stations.size=" + stations.size());
    }
    boolean found = false;
    if (currentStation.length() < 1) {
      found = true;
    }
    TreeMap sensorsC = (TreeMap) sensors.clone();
    Iterator it = sensorsC.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String info[] = ylib.csvlinetoarray((String) sensors.get(key));
      String port = (String) stations.get(info[0]);
      if (port != null) {

        if (currentStation.equals(info[0]) && currentId.equals(info[1])) {
          found = true;
        }        
        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("key=" + key + ",info.length=" + info.length + ",info[0]=" + info[0] + ",info[1]=" + info[1] + ",info[22]=" + (info.length > 22 ? info[22] : "")
                  + ",info[23]=" + (info.length > 23 ? info[23] : "") + ",info[24]=" + (info.length > 24 ? info[24] : "") + ",info[25]=" + (info.length > 25 ? info[25] : "") + ",port=" + port + ",found=" + found);
        }
        if (found) {
          if (currentStation.equals("") || (!(currentStation.equals(info[0]) && currentId.equals(info[1])))) {
            if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
              sysLog("next device id=" + info[1]);
            }
            return new String[]{info[0], info[1], port};

          }
        }
      }
    }
    return null;
  }

  public String[] getToId_m(String key) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("getToId_m(" + key + "), sensors.size=" + sensors.size() + ",stations.size=" + stations.size());
    }      

    if (sensors.get(key) != null) {
      String info[] = ylib.csvlinetoarray((String) sensors.get(key));
      String port = (String) stations.get(info[0]);
      if (port != null) {

        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("key=" + key + ",info.length=" + info.length + ",info[0]=" + info[0] + ",info[1]=" + info[1] + ",info[22]=" + (info.length > 22 ? info[22] : "")
                  + ",info[23]=" + (info.length > 23 ? info[23] : "") + ",info[24]=" + (info.length > 24 ? info[24] : "") + ",info[25]=" + (info.length > 25 ? info[25] : "") + ",port=" + port);
        }        

        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog("result=" + info[0] + "-" + info[1]);
        }
        return new String[]{info[0], info[1], port};

      }
    }

    return null;
  }
  

  public void putCmd_c(String ids[]) {
    if (ids[2].indexOf(":") > -1) {
    } else {
      if (!onlyReceiveCB.isSelected()) {
        String cmd = "CC FF 13 67 " + ids[4] + " " + ids[3] + " 01 16 FF CC";

        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog(wn.getPort(ids[0]) + "-out1,cmd=" + cmd);
        }
        cmd = wn.w.e642(cmd);
        cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
        wn.w.sendToOne(cmd, wn.w.getGNS(1));
        lastIssueCmdTime_c = System.currentTimeMillis();
      }
    }
  }

  public void putCmd_m(String ids[]) {
    if (ids[2].indexOf(":") > -1) {
    } else {
      if (!onlyReceiveCB.isSelected()) {
        String cmd = ids[1] + " 04 10 04 00 08";
        cmd = wn.w.e642(cmd);
        cmd = "performcommand wsn.WSN cmd all all true true false 5 " + cmd + " " + wn.w.e642("Modbus CRC") + " 0 0 0 0 0";
        if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
          sysLog(wn.getPort(ids[0]) + "-out1,cmd=" + cmd);
        }
        wn.w.sendToOne(cmd, wn.w.getGNS(1));
        lastIssueCmdTime_m = System.currentTimeMillis();
      }
    }
  }

  void updateData_c() {
    cycleOn = true;
    cycleStartTime = System.currentTimeMillis();

    String toId[] = getNext_c("", "", 2);

    if (toId != null) {
      putCmd_c(toId);
      waitDeviceAddr_c = toId[4];
      waitDeviceTime_c = System.currentTimeMillis();
      waitKeyAction_m = 2;
      waitKeyActionCount_m = 0;
    } else {
      cycleOn = false;
      cycleStartTime = 0;
    }
  }

  void updateData_m() {
    

    cycleOn = true;
    cycleStartTime = System.currentTimeMillis();

    String toId[] = getNext_m("", "", 2);

    if (toId != null) {
      putCmd_m(toId);
      waitKey_m = toId[0] + "," + toId[1];
      waitKeyTime_m = System.currentTimeMillis();
      waitKeyAction_m = 2;
      waitKeyActionCount_m = 0;
    } else {
      cycleOn = false;
      cycleStartTime = 0;
    }
  }

  class WaitThread extends Thread {

    boolean isSleep = false;

    public void run() {
      while (true) {
        while (waitV.size() > 0) {
          String work = (String) waitV.get(0);
          int inx = work.indexOf("-");
          String msg = work.substring(inx + 1);
          switch (Integer.parseInt(work.substring(0, inx))) {
            case 1:
              sendEmail(1,"cr-Instrument alert message",msg);
              break;
            case 2:
              sendSms(1,msg);

              break;
          }
          waitV.remove(0);
        }
        try {
          isSleep = true;
          sleep(1000L * 60L * 60L * 24L);
        } catch (InterruptedException e) {
        }
        isSleep = false;
      }
    }

    public void setWork(int type, String msg) {
      if (msg.length() > 0) {
        waitV.add(type + "-" + msg);
        if (isSleep) {
          interrupt();
        }
      }
    }
  }
  public boolean chkEmailTime(){
     boolean rtn=false;
      long emailSpan = 0;
        emailSpan = getPropsLong("email-time-h") * 1000L * 60L * 60L + getPropsLong("email-time-m") * 1000L * 60L;
        if (emailSpan == 0L) {
          emailSpan = 24L * 1000L * 60L * 60L;
        }
        long lastEmailTime = 0;
        try {
          if (statuses.getProperty("last-email-time") != null && statuses.getProperty("last-email-time").trim().length() == 19) {
            lastEmailTime = format9.parse(statuses.getProperty("last-email-time")).getTime();
          }
        } catch (ParseException e) {
             sysLog("Error: ParseException in chkEmailTime() for last-email-time '"+statuses.getProperty("last-email-time")+"'");
        }

        if (lastEmailTime == 0 || (System.currentTimeMillis() - lastEmailTime) > emailSpan) {
          rtn=true;
        }

      return rtn;
  }
  public boolean chkSmsTime(){
      boolean rtn=false;
      long smsSpan = 0;
        smsSpan = getPropsLong("sms-time-h") * 1000L * 60L * 60L + getPropsLong("sms-time-m") * 1000L * 60L;
        if (smsSpan == 0L) {
          smsSpan = 24L * 1000L * 60L * 60L;
        }
        long lastSmsTime = 0;
        try {
          if (statuses.getProperty("last-sms-time") != null && statuses.getProperty("last-sms-time").trim().length() == 19) {
            lastSmsTime = format9.parse(statuses.getProperty("last-sms-time")).getTime();
          }
        } catch (ParseException e) {
            sysLog("Error: ParseException in chkSmsTime() for last-sms-time '"+statuses.getProperty("last-sms-time")+"'");
        }

        if (lastSmsTime == 0 || (System.currentTimeMillis() - lastSmsTime) > smsSpan) {
          rtn=true;
        }

      return rtn;
  }

  public boolean sendEmail(int type,String subject,String msg){
    boolean rtn=false;
      if (!OneVar.check(props.getProperty("lky-n"), 2)) {
      if(type==1) {
          sysLog("email inform (not sent, for system function disabled reason): " + msg);
      }
      else JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg28"));
      return false;
    }
              String email = jTextField6.getText().trim();
              String pw = new String(jPasswordField7.getPassword());
              if (email.length() < 5 || pw.length() < 1) {
                if(type==1) sysLog("email failed: "+bundle2.getString("CrInstrument.xy.msg29")+"(email=" + email + ",pw=" + pw + ")。");
                else JOptionPane.showMessageDialog(this,"email failed: "+bundle2.getString("CrInstrument.xy.msg30")+"(email=" + email + ",pw=" + pw + ")。");
                return false;
              }
              String emailSp=getPropsString("email-sp");
              if (emailSp==null || emailSp.length() < 1) {
                if(type==1) sysLog("email failed: No Email Service Provider. \r\nPlease config it in properties file '"+propFile+"'.");
                else JOptionPane.showMessageDialog(this,"email failed: No Email Service Provider. \r\nPlease config it in properties file '"+propFile+"'.");
                return false;
              }
              String emailSpInfo[];
              if(emailSpTM.get(emailSp)!=null){
                  emailSpInfo=ylib.csvlinetoarray((String)emailSpTM.get(emailSp));
              } else {
                if(type==1) sysLog("email failed: No EMail Service Provider Data of '"+emailSp+"'. \r\nPlease config it in email sp file '"+emailSpFile+"'.");
                else JOptionPane.showMessageDialog(this,"email failed: No EMail Service Provider Data of '"+emailSp+"'. \r\nPlease config it in email sp file '"+emailSpFile+"'.");
                return false;
              }
              String mailTo[] = ylib.csvlinetoarray(getPropsString("email-to"));
              boolean chkMail = true;
              for (int i = 0; i < mailTo.length; i++) {
                if(mailTo[i].trim().length()<1){
                  if(type==1) sysLog("Email failed, wrong email format: empty email.");
                  else JOptionPane.showMessageDialog(this,"Email failed, wrong email format: empty email.");
                  chkMail = false;
                  break;
                } else if (!isValidEmailAddress(mailTo[i])) {
                  if(type==1) sysLog("Email failed, wrong email format:" + mailTo[i]);
                  else JOptionPane.showMessageDialog(this,"Email failed, wrong email format:" + mailTo[i]);
                  chkMail = false;
                  break;
                }
              }
              if (chkMail) {
                if(emailSp.equalsIgnoreCase("gmail")){
                  String oldLastTime=statuses.getProperty("last-email-time");
                  if(type==1) statuses.put("last-email-time", format9.format(new Date(System.currentTimeMillis())));
                GMailThread2.Item item = new GMailThread2.Item(0, "", "", "", subject, msg, null, null, mailTo, "true", email, pw,
                        "text/plain", "Big5", "", "", false, "", "", "", "");

                if (gm2.sendMail(item)) {
                  if(type==1) sysLog("Email msg:" + msg);

                  rtn=true;
                } else {
                  if(type==1) sysLog("Email failed, email msg:" + msg);
                  if(type==1) statuses.put("last-email-time", oldLastTime);
                  else JOptionPane.showMessageDialog(this,"Email failed, email msg:" + msg);
                }
                }
                else {
                    if(type==1) sysLog("Email failed, msg: no email program for email service provider '" + emailSp+"'.");
                    else JOptionPane.showMessageDialog(this,"Email failed, msg: no email program for email service provider '" + emailSp+"'.");
                }
              }
     return rtn;
  }

  public boolean sendSms(int type,String msg){
    boolean rtn=false;
    try{
        if (!OneVar.check(props.getProperty("lky-n"), 3)) {
           if(type==1) {
           sysLog("sms inform (not sent, for system function disabled reason): " + msg);
           }else JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg31"));
        return false;
    }

    if(getPropsString("sms-to").length()<5) { 
        if(type==1) sysLog( bundle2.getString("CrInstrument.xy.msg32")); 
        else JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg33")); 
        return false; 
    }

      String sms = jTextField2.getText().trim();
              String pw2 = new String(jPasswordField5.getPassword());
              if (sms.length() < 1 || pw2.length() < 1) {
                if(type==1) sysLog(bundle2.getString("CrInstrument.xy.msg34"));
                else JOptionPane.showMessageDialog(this,bundle2.getString("CrInstrument.xy.msg35"));
                return rtn;
              }
              String smsSp=getPropsString("sms-sp");
              if (smsSp==null || smsSp.length() < 1) {
                if(type==1) sysLog("sms failed: No SMS Service Provider. \r\nPlease config it in properties file '"+propFile+"'.");
                else JOptionPane.showMessageDialog(this,"sms failed: No SMS Service Provider. \r\nPlease config it in properties file '"+propFile+"'.");
                return rtn;
              }
              String smsSpInfo[];
              if(smsSpTM.get(smsSp)!=null){
                  smsSpInfo=ylib.csvlinetoarray((String)smsSpTM.get(smsSp));
              } else {
                if(type==1) sysLog("sms failed: No SMS Service Provider Data of '"+smsSp+"'. \r\nPlease config it in sms sp file '"+smsSpFile+"'.");
                else JOptionPane.showMessageDialog(this,"sms failed: No SMS Service Provider Data of '"+smsSp+"'. \r\nPlease config it in sms sp file '"+smsSpFile+"'.");
                return rtn;
              }
              String sendTo[] = ylib.csvlinetoarray(getPropsString("sms-to"));
              String logfile = "data\\smslog.txt";
              String result = "unknown";
              String failMsg = "";
              msg=ylib.replace(msg, "\r", "%0D");
              msg=ylib.replace(msg, "\n", "%0A");
              msg=ylib.replace(msg, " ", "%20");
              String oldLastTime=statuses.getProperty("last-sms-time");
              if(type==1) statuses.put("last-sms-time", format9.format(new Date(System.currentTimeMillis())));
              for (int i = 0; i < sendTo.length; i++) {
                if (sendTo[i].length() == 10) {
                  failMsg = "";
                  String smsString=ylib.replace(smsSpInfo[2], "[#account#]",sms);
                  smsString=ylib.replace(smsString, "[#password#]",pw2);
                  smsString=ylib.replace(smsString, "[#sendToPhone#]",sendTo[i]);
                  smsString=ylib.replace(smsString, "[#message#]",msg);
                  if(urlfiletodisk(smsString, logfile)){

                  try {

                    FileInputStream in = new FileInputStream(logfile);
                    BufferedReader d = new BufferedReader(new InputStreamReader(in));

                    while (true) {

                      String str1 = d.readLine();

                      if (str1 == null) {
                        in.close();
                        d.close();
                        break;
                      }
                      if (str1.length() > 3) {
                        str1 = str1.trim();
                        if (smsSpInfo[0].equalsIgnoreCase("SMS King") && str1.indexOf("kmsgid=") > -1 & str1.length() > 7) {
                          String nmu = str1.substring(7);
                          if (wn.isNumeric(nmu)) {
                            if (Integer.parseInt(nmu) > 0) {
                              result = "successful";
                            } else {
                              result = "failed";
                              if (nmu.equals("-1")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg36");
                              } else if (nmu.equals("-2")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg37");
                              } else if (nmu.equals("-4")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg38");
                              } else if (nmu.equals("-5")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg39");
                              } else if (nmu.equals("-6")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg40");
                              } else if (nmu.equals("-20")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg41");
                              } else if (nmu.equals("-21")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg42");
                              } else if (nmu.equals("-59999")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg43");
                              } else if (nmu.equals("-60002")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg44");
                              } else if (nmu.equals("-60014")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg45");
                              } else if (nmu.equals("-999959999")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg46");
                              } else if (nmu.equals("-999969999")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg47");
                              } else if (nmu.equals("-999979999")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg48");
                              } else if (nmu.equals("-999989999")) {
                                failMsg = bundle2.getString("CrInstrument.xy.msg49");
                              }
                            }
                          }
                          break;
                        } else {

                        }
                      }
                    }

                  } catch (IOException e) {

                    if(type==1) sysLog("reading file (filename=" + logfile + ") IOException, error message: " + e.getMessage() + "\r\n");
                    else JOptionPane.showMessageDialog(this,"reading file (filename=" + logfile + ") IOException, error message: " + e.getMessage() + "\r\n");
                    e.printStackTrace();
                  } catch (Exception e) {

                    if(type==1) sysLog("reading file (filename=" + logfile + ") error, error message: " + e.getMessage() + "\n");
                    else JOptionPane.showMessageDialog(this,"reading file (filename=" + logfile + ") error, error message: " + e.getMessage() + "\n");
                    e.printStackTrace();
                  }
                  } else {result="failed"; failMsg="failed to call url, please check url address '"+smsSpInfo[2]+"'.";}
                  if(type==1) sysLog("Sending sms, sms msg:" + msg + ", result: " + result + (failMsg.length() > 0 ? "\r\nfailed msg: " + failMsg : ""));

                }
                else {
                  if(type==1) sysLog("Wrong sms phone number:" + sendTo[i]);
                  else JOptionPane.showMessageDialog(this,"Wrong sms phone number:" + sendTo[i]);
                }
              }
              if(result.equalsIgnoreCase("successful")){

                  if(type==2) JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg50") +" "+ sms + " "+bundle2.getString("CrInstrument.xy.msg51"));
              } else if(result.equalsIgnoreCase("failed")){
                  String logMsg=bundle2.getString("CrInstrument.xy.msg52")+"\r\n"+bundle2.getString("CrInstrument.xy.msg53")+" " + failMsg + ", \r\n"+bundle2.getString("CrInstrument.xy.msg54")+"\r\n" 
              + "1."+bundle2.getString("CrInstrument.xy.msg55")+" \""+smsSpInfo[0]+"\" "+bundle2.getString("CrInstrument.xy.msg56")+"\r\n" 
              + "2."+bundle2.getString("CrInstrument.xy.msg57")+" '"+smsSpInfo[5]+"'"; 
                  if(type==1) sysLog(logMsg);
                  if(type==1) statuses.put("last-sms-time", oldLastTime);
                  else JOptionPane.showMessageDialog(this, logMsg);
              } else {
                  if(type==2) JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg58"));
              }
              rtn=true;
      } catch(Exception e){
          e.printStackTrace();
          if(type==1) sysLog("Error: failed to sending sms, message: " + e.getMessage());
          else JOptionPane.showMessageDialog(this,"Error: failed to sending sms, message: " + e.getMessage());
      }
    return rtn;
}
  public static boolean isValidEmailAddress(String email) {
    boolean result = true;
    try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
    } catch (AddressException ex) {
      result = false;
    }
    return result;
  }

  class WaitThread2 extends Thread {

    long waitTime = 1000L;
    boolean isSleep = false;
    int type = 0, 
            cnt = 0;
    String para1 = "", para2 = "", para3 = "";

    public void run() {
      while (true) {
        try {
          isSleep = true;
          if (type > 0) {
            sleep(waitTime);
            switch (type) {
              case 1:

                if (!onlyReceiveCB.isSelected()) {
                  String toId[] = getNext_c("", "", 1);
                  if (toId != null) {
                    String cmd = "CC FF 09 07 " + toId[4] + " FF CC";
                    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
                      sysLog(wn.getPort(para2) + "-out3,cmd=" + cmd);
                    }
                    cmd = wn.w.e642(cmd);
                    cmd = "performcommand wsn.WSN cmd all all true false false 5 " + cmd + " 0 0 0 0 0 0";
                    wn.w.sendToOne(cmd, para3);
                    lastIssueCmdTime_m = System.currentTimeMillis();
                  }
                }

                break;
              case 2:
                break;
              case 3:
                break;
            }
            type = 0;
          } else {
            sleep(1000L * 60L * 60L * 24L);
          }
        } catch (InterruptedException e) {

        }
        isSleep = false;
      }
    }

    public void setType(int type, int cnt, long wtime, String pa1, String pa2, String pa3) {
      this.type = type;
      this.cnt = cnt;
      this.waitTime = wtime;
      this.para1 = pa1;
      this.para2 = pa2;
      this.para3 = pa3;
      if (isSleep) {
        interrupt();
      }
    }

    public void cancel(int type) {
      this.type = 0;
      if (isSleep) {
        interrupt();
      }
    }
  }

  class SaveRecordThread extends Thread {

    long waitTime = 1000L, nextTime = 0L, lastTime = 0L;
    boolean isSleep3 = false;

    public void run() {
      while (true) {
        long now = System.currentTimeMillis();
        if (lastTime == 0) {
          lastTime = now;
        }
        long interval = getPropsLong("record-interval-h") * 1000L * 60L * 60L + getPropsLong("record-interval-m") * 1000L * 60L + getPropsLong("record-interval-s") * 1000L;
        if (interval < 1000L) {
          interval = 1000L * 60L * 60L * 24L * 365L;
        }
        try {
          if (updateHistoryRecord) {
            updateHistoryFile(3);
          }
          

          if (!chkProps("recordwhenreceive") && !(getPropsInt("monitor-interval-h") == getPropsInt("record-interval-h")
                  && getPropsInt("monitor-interval-m") == getPropsInt("record-interval-m") 
                  && getPropsInt("monitor-interval-s") == getPropsInt("record-interval-s"))
                  && lastDataTime > lastRecordTime) {
            saveData3(lastTime, lastTime, 7);
          }
          now = System.currentTimeMillis();
          nextTime = lastTime + interval;

          isSleep3 = true;
          if (!chkProps("recordwhenreceive") && !(getPropsInt("monitor-interval-h") == getPropsInt("record-interval-h")
                  && getPropsInt("monitor-interval-m") == getPropsInt("record-interval-m") && getPropsInt("monitor-interval-s") == getPropsInt("record-interval-s"))) {
            waitTime = nextTime - now;
          } else {
            waitTime = 1000L * 60L * 60L * 24L * 365L;
          }               
          if (waitTime > 0) {
            sleep(waitTime);
          }
          lastTime = nextTime;
        } catch (InterruptedException e) {
          lastTime = 0L;
        }
        isSleep3 = false;
      }
    }

    public void setBegin() {
      if (saveRecordThread.isSleep3) {
        saveRecordThread.interrupt();
      }
    }
  }

  public boolean urlfiletodisk(String netFile, String localFile) {

    if (netFile.length() < 8 || localFile.length() < 1) {
      return false;
    }
    try {
      URL uIn = new URL(netFile);
      URLConnection uc = uIn.openConnection();
      File f = new File(localFile);
      InputStream in = uIn.openStream();
      FileOutputStream out = new FileOutputStream(f);
      copyStream(in, out);
      FileDescriptor fd = out.getFD();
      fd.sync();
      in.close();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public void copyStream(InputStream in, OutputStream out) throws IOException {
    try {
      synchronized (in) {
        synchronized (out) {
          byte[] buffer = new byte[512];
          while (true) {
            int bytesRead = in.read(buffer);
            if (bytesRead == -1) {
              out.flush();
              break;
            }
            out.write(buffer, 0, bytesRead);
          }
        }
      }
    } catch (IOException e) {
      throw e;
    }
  }

  public void showCurve(String curSensor) {

    if (allDatum.get(curSensor) != null) {

      Config cfgA=(Config) allConfigTM.get(curSensor);
      Status staA=(Status) allStatusTM.get(curSensor);

      if (cbRemark.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 6);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 6);
      }
      if (showAlarmRB.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 10);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 10);
      }
      if (cbAutoAdjustY.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 14);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 14);
      }
      if (cbZero.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 15);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 15);
      }
      allConfigTM.put(curSensor,cfgA);
      staA.longValue[6] = 0;
      allStatusTM.put(curSensor,staA);
      multiPanel.setTMData(0, allDatum, allConfigTM, allStatusTM, curSensor);
      multiPanel.repaint();

    } 
    else if (multiPanel != null) {

      String info[] = ylib.csvlinetoarray((String) sensors.get(curSensor));
      Config cfgA=(Config) getConfig(curSensor);
      Status staA=(Status) getStatus(curSensor);

      if (cbRemark.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 6);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 6);
      }
      if (showAlarmRB.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 10);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 10);
      }
      if (cbAutoAdjustY.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 14);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 14);
      }
      if (cbZero.isSelected()) {
        cfgA.longValue[0] = OneVar.add(cfgA.longValue[0], 15);
      } else {
        cfgA.longValue[0] = OneVar.remove(cfgA.longValue[0], 15);
      }
      allConfigTM.put(curSensor,cfgA);
      staA.longValue[6] = 0;
      allStatusTM.put(curSensor,staA);

      String para[] = {};
      if(info.length>1) para=new String[]{info[0] + " (ID:" + info[1] + ")"};
      multiPanel.setPara(0, para);

      multiPanel.setTMData(0, null, allConfigTM, allStatusTM, curSensor);
      multiPanel.repaint();
    }
  }
  public static final Pattern Num_PATTERN = Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$");

  public static boolean isNumeric(String s) {
    return (s == null ? false : Num_PATTERN.matcher(s).matches());
  }
  

  public static boolean isNumeric2(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");  
  }
  

  public static boolean isNumeric3(String str) {
    NumberFormat formatter = NumberFormat.getInstance();
    ParsePosition pos = new ParsePosition(0);
    formatter.parse(str, pos);
    return str.length() == pos.getIndex();
  }

  void read_ci_Props() {
    if (wn.getPropsString("ci_props_file").length() > 0) {
      propFile = wn.getPropsString("ci_props_file");
      propFile = propFile.replace('\\', File.separatorChar);
      propFile=propFile.replace('/',File.separatorChar);
      if (new File(propFile).exists()) {
        InputStream is = null;
        try {
          File f = new File(propFile);
          is = new FileInputStream(f);
        } catch (Exception e) {
          is = null;
        }
        try {
          if (is == null) {

            is = getClass().getResourceAsStream(propFile);
          }

          props.load(is);
          if (getPropsString("moduletype") == null) {
            props.setProperty("moduletype", "2");
          }
          if (getPropsString("setting-pw") == null) {
            props.setProperty("setting-pw", YB642E.encode("1234"));
          }
          if (getPropsString("mgnt-pw") == null) {
            props.setProperty("mgnt-pw", YB642E.encode("111"));
          }
          if (getPropsString("defaultfileformat") == null) {
            props.setProperty("defaultfileformat", "*.dat");
          }
          if (props.getProperty("record-interval-h") == null || props.getProperty("record-interval-h").length() < 1) {
            props.setProperty("record-interval-h", "0");
          }
          if (props.getProperty("record-interval-m") == null || props.getProperty("record-interval-m").length() < 1) {
            props.setProperty("record-interval-m", "0");
          }
          if (props.getProperty("record-interval-s") == null || props.getProperty("record-interval-s").length() < 1) {
            props.setProperty("record-interval-s", "0");
          }
          if (props.getProperty("monitor-interval-h") == null || props.getProperty("monitor-interval-h").length() < 1) {
            props.setProperty("monitor-interval-h", "24");
          }
          if (props.getProperty("monitor-interval-m") == null || props.getProperty("monitor-interval-m").length() < 1) {
            props.setProperty("monitor-interval-m", "0");
          }
          if (props.getProperty("monitor-interval-s") == null || props.getProperty("monitor-interval-s").length() < 1) {
            props.setProperty("monitor-interval-s", "0");
          }
          if (props.getProperty("show_datatype") == null || props.getProperty("show_datatype").length() < 1) {
            props.setProperty("show_datatype", "raw");
          }
          if (chkProps("up-alarm-sms")) {
            props.setProperty("down-alarm-sms", "Y");
          }
          if (chkProps("up-action-sms")) {
            props.setProperty("down-action-sms", "Y");
          }
          if (chkProps("up-alarm-email")) {
            props.setProperty("down-alarm-email", "Y");
          }
          if (chkProps("up-action-email")) {
            props.setProperty("down-action-email", "Y");
          }     
          if (wn.w.getValueString(props.getProperty("logfile-head")).length() > 0) {
            logFileHead = wn.w.getValueString(props.getProperty("logfile-head"));
            logFileHead = logFileHead.replace('\\', File.separatorChar);
          }
          if (wn.w.getValueString(props.getProperty("data-dir")).length() > 0) {
            dataDir = wn.w.getValueString(props.getProperty("data-dir"));
            dataDir = dataDir.replace('\\', File.separatorChar);
          } else dataDir="ci-data";
          if (wn.w.getValueString(props.getProperty("log-dir")).length() > 0) {
            logDir = wn.w.getValueString(props.getProperty("log-dir"));
            logDir = logDir.replace('\\', File.separatorChar);
          } else logDir="ci-log";
          if (!new File(logDir).exists()) {
            new File(logDir).mkdirs();
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("properties file " + propFile + " not found.");
      }
    } else {
      sysLog("ci_props_file undefied.");
    }
  }

  void readStatus() {
    if (wn.w.getValueString(props.getProperty("status-file")).length() > 0) {
      statusFile = wn.w.getValueString(props.getProperty("status-file"));
      statusFile = statusFile.replace('\\', File.separatorChar);
      statusFile=statusFile.replace('/', File.separatorChar);
    File f = new File(statusFile);
    if (f.exists()) {
      InputStream is = null;
      try {
        is = new FileInputStream(f);
      } catch (Exception e) {
        is = null;
      }
      try {
        if (is == null) {

          is = getClass().getResourceAsStream(statusFile);
        }

        statuses.load(is);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else sysLog("status file "+statusFile+" not found.");
    if (statuses.getProperty("last-sms-time") == null) {
      statuses.put("last-sms-time", "");
    }
    if (statuses.getProperty("last-email-time") == null) {
      statuses.put("last-email-time", "");
    }
    } else sysLog("status file undefined.");
  }
  

  public static int getCRC(byte[] buf, int len) {
    int crc = 0xFFFF;
    int val = 0;

    for (int pos = 0; pos < len; pos++) {
      crc ^= (int) (0x00ff & buf[pos]);  

      for (int i = 8; i != 0; i--) {    
        if ((crc & 0x0001) != 0) {      
          crc >>= 1;                    
          crc ^= 0xA001;
        } else 
        {
          crc >>= 1;                    
        }
      }
    }

    val = (crc & 0xff) << 8;
    val = val + ((crc >> 8) & 0xff);

    return val;
  }   
  void saveEvents() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = eventTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(eventFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveUI() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = currentUI.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(uiFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveConditions() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = conditionTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(conditionFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveActions() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = actionTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(actionFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveSmsSp() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = smsSpTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(smsSpFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveEmailSp() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = emailSpTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(emailSpFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveCharts() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = chartTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(chartFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveCurves() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    StringBuffer sb = new StringBuffer();
    Iterator it = curveTM.values().iterator();
    boolean first = true;
    for (; it.hasNext();) {
      sb.append((first ? "" : "\r\n") + (String) it.next());
      first = false;
    }
    try {
      FileOutputStream out = new FileOutputStream(curveFile);
      byte[] b = sb.toString().getBytes();
      out.write(b);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  void saveStatus() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    try {
      FileOutputStream out = new FileOutputStream(statusFile);
      statuses.store(out, "");
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void saveProps() {
    if(wn.w.chkValue(props.getProperty("ci-demo")) && wn.chkProps("run_my_ap_only")) return;
    try {

      FileOutputStream out = new FileOutputStream(propFile);
      props.store(out, "");
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setBlink(boolean bln) {
    n120++;
    if(needChk){receiveTP.setCaretPosition(styleDoc.getLength()); needChk=false;}
    if(dataUpdated || changeStation) {
      showStation(); 

      dataUpdated = false;
      changeStation=false;
    }

    if (bln) {
      jLabel13.setText(format4.format(new Date()));
      

      if (chkProps("recordwhenreceive") || (getPropsInt("monitor-interval-h") == getPropsInt("record-interval-h")
              && getPropsInt("monitor-interval-m") == getPropsInt("record-interval-m") && getPropsInt("monitor-interval-s") == getPropsInt("record-interval-s"))) {
        if (!onlyReceiveCB.isSelected()) {
          if (!continueMonitor) {
            if (updateHistoryRecord) {
              updateHistoryFile(4);
            }
            if (lastDataTime > lastRecordTime) {

              saveData3(lastDataTime, System.currentTimeMillis(), 1);
            }
          }
        }
        else {

          long time = lastDataTime - lastLastDataTime;
          long diff = System.currentTimeMillis() - lastDataTime;
          if (updateHistoryRecord) {
            updateHistoryFile(5);
          }
          if ((diff > 180000L || diff > (time + 3000L)) && lastDataTime > lastRecordTime) {
            saveData3(lastDataTime, System.currentTimeMillis(), 2);
          }
        }
      } else {
        

        if (updateHistoryRecord) {
          updateHistoryFile(6);
        }
      }
    } else {
      

    }
    if (currentLightStat != currentStat && continueMonitor);
    currentLightStat = currentStat;
  }

public void setStatus(String nodeId,String dataSrc[],int statusCode){
  if(dataSrc!=null && dataSrc.length>0 && dataSrc[0].startsWith("device@")) return;
  if(!dataSrc[0].equals("0")){
    for(int i=0;i<dataSrc.length;i++) eventThread.setStatus(nodeId,dataSrc[i],statusCode);
    for(int i=0;i<dataSrc.length;i++){

      if(statusCode==1 || statusCode==3) {
         nameIdMap.put(wn.getPort(dataSrc[i]), nodeId);
      } else if(statusCode==2){
          nameIdMap.remove(wn.getPort(dataSrc[i]));
      }
    }
    updateList();
  }
}
  

  public void setData(long time, String nodeId, String dataSrc, String data) {
    lastReceiveTime = time;
    dataSrc=wn.getPort(dataSrc);
    if(continueMonitor) {
      if(showCB.isSelected()) showThread.setData(time, nodeId, dataSrc, data);
      eventThread.setData(time, nodeId, dataSrc, data);
      if(saveFileCB.isSelected()) fileThread.setData(time, nodeId, dataSrc, data);
    }
  }
  public void textPaneAppend(String temp2){
       textPaneAppend(temp2,null,0);

  }

  public void textPaneAppend(String temp2,Color col,int fontSize){

     displayV.add(new WSNDisplayData(temp2,col,fontSize));
      Runnable  runnable = new Runnable() {
        public void run(){
         if(receiveTP.getText().length()>maxMainLogLength) {
            if(saveFileCB.isSelected()) fileThread.setData(0,"0","0",  receiveTP.getText().trim());

             try{

               styleDoc.remove(0, styleDoc.getLength());
               beginTextPane=true;
             } catch(BadLocationException e){
                e.printStackTrace();
              }
            lastIsData=false;
         }
         WSNDisplayData dData=(WSNDisplayData)displayV.get(0);
            try   {   
                SimpleAttributeSet   attrSet   =   new   SimpleAttributeSet();
                if(dData.fontColor!=null) StyleConstants.setForeground(attrSet, dData.fontColor);   
                if(dData.fontSize>0) StyleConstants.setFontSize(attrSet,   dData.fontSize);  
                styleDoc.insertString(styleDoc.getLength(), dData.data,  attrSet);
                beginTextPane=false;
            }   catch   (BadLocationException   e)   {   
               System.out.println("BadLocationException:   "   +   e);   
                }   

              displayV.remove(0);
              needChk=true;
            }
        };
        SwingUtilities.invokeLater(runnable);
  }

  void insertToCoorDevices(String coorAddr, String info[]) {
    TreeMap tm = (TreeMap) coorDevices.get(coorAddr);
    if (tm == null) {
      tm = new TreeMap();
    }
    tm.put(info[1], info[23]);
    coorDevices.put(coorAddr, tm);
  }

  String insertToCoorDevices_old(String coorAddr, String deviceAddr) {
    TreeMap tm = (TreeMap) coorDevices.get(coorAddr);
    if (tm == null) {
      tm = new TreeMap();
    }
    String id = "01";
    int inx = 0;
    boolean foundGap = false, alreadyHas = false;
    Iterator it = tm.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String device = (String) tm.get(key);
      if (device.equals(deviceAddr)) {
        alreadyHas = true;
        break;
      }
      int inx2 = Integer.parseInt(key);
      inx++;
      if (inx2 > inx) {
        foundGap = true;
        id = (inx > 9 ? "" : "0") + inx;
        break;
      };
    }
    if (!alreadyHas) {
      if (!foundGap) {
        inx = tm.size() + 1;
        id = (inx > 9 ? "" : "0") + inx;
      }
      tm.put(id, deviceAddr);
    }
    coorDevices.put(coorAddr, tm);
    return id;
  }

  

  String getNewIdFromStation(String station) {
    String id = "01";
    int inx = 0;
    boolean foundGap = false;
    Iterator it = sensors.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();
      String info[] = ylib.csvlinetoarray(key);
      if (info[0].equals(station)) {
        int inx2 = Integer.parseInt(info[1]);
        inx++;
        if (inx2 > inx) {
          foundGap = true;
          id = (inx > 9 ? "" : "0") + inx;
          break;
        };
      }
    }
    if (!foundGap) {
      inx = inx + 1;
      id = (inx > 9 ? "" : "0") + inx;
    }
    return id;
  }

  

  @Override
public void doLayout(){
  super.doLayout();
  String info[];
  updateTitle();
  int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
  int frameWidth = 1024;
  int frameHeight = 712;
  if(currentUI.get("frame")!=null){
    String frameInfo=(String)currentUI.get("frame");
    if(!frameInfo.equals(preFrameInfo)) {
    info=ylib.csvlinetoarray(frameInfo);

    if(info.length > 5 && info[5].length()>0){
        if(info[2].equals("%")) frameWidth=(int)(Double.parseDouble(info[5]) * ((double)screenWidth)); else frameWidth=Integer.parseInt(info[5]);
    }
    if(info.length > 6 && info[6].length()>0){
        if(info[2].equals("%")) frameHeight=(int)(Double.parseDouble(info[6]) * ((double)screenHeight)); else frameHeight=Integer.parseInt(info[6]);
    }
    if(info.length > 13 && info[13].equalsIgnoreCase("f")){
       device=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      if(device.isFullScreenSupported()){
         device.setFullScreenWindow(this);
        }
    } else {

        int x=(screenWidth - frameWidth) / 2,y=(screenHeight-20 - frameHeight) / 2 - 10;
        if(info.length>8 && info[8].equalsIgnoreCase("c")){
           setLocation((screenWidth - frameWidth) / 2, (screenHeight -20 - frameHeight) / 2 - 10);
        } else {
            if(info.length > 3 && info[3].length()>0){
              if(info[2].equals("%")) x=(int)(Double.parseDouble(info[3]) * ((double)screenWidth)); else x=Integer.parseInt(info[3]);
            }
            if(info.length > 4 && info[4].length()>0){
              if(info[4].equals("%")) y=(int)(Double.parseDouble(info[4]) * ((double)screenHeight)); else y=Integer.parseInt(info[4]);
            }
           }
        if(info[2].equals("%") && x==100 && y==100){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }  else {
             setSize(frameWidth, frameHeight);
             setLocation(x, y);
        }
        if(info.length>14 && info[14].equalsIgnoreCase("r")) setResizable(true); else setResizable(false);
    }
    jPanel1.setBackground((info.length>7 && info[7].length()>0 && wn.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):jPanel1.getBackground());
    preFrameInfo=frameInfo;
    }

  }
  if(currentUI.get("button 01")!=null){
    info=ylib.csvlinetoarray((String)currentUI.get("button 01"));
    if(info.length>2 && info[2].equalsIgnoreCase("s")){
        button01.setVisible(true);
        if(info.length>1 && info[1].length() >0) button01.setText(" "+info[1]+ " ");
        Font font=button01.getFont();
        button01.setFont(getFont(font,font.getSize(),info[8],info[9],info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
        button01.setBackground((info.length>7 && info[7].length()>0 && wn.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):button01.getBackground());
        button01.setForeground((info.length>10 && info[10].length()>0 && wn.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):button01.getForeground());
    } else  button01.setVisible(false);
  }
  setButton("button 02",button02,frameWidth,frameHeight);
  setButton("button 03",button03,frameWidth,frameHeight);
  setButton("button 04",button04,frameWidth,frameHeight);
  setButton("button 05",button05,frameWidth,frameHeight);
  setButton("button 06",button06,frameWidth,frameHeight);
  setButton("button 07",button07,frameWidth,frameHeight);
  setButton("button 08",button08,frameWidth,frameHeight);
  setButton("button 09",button09,frameWidth,frameHeight);
  setButton("button 10",button10,frameWidth,frameHeight);
  setButton("connect button",btnConnect,frameWidth,frameHeight);
  setButton("start button",btnStart,frameWidth,frameHeight);
  setMenuItem("file menuitem 01",fileMenuItem01,frameWidth,frameHeight);
  setMenuItem("file menuitem 02",fileMenuItem02,frameWidth,frameHeight);
  setMenuItem("file menuitem 03",fileMenuItem03,frameWidth,frameHeight);
  setMenuItem("file menuitem 04",fileMenuItem04,frameWidth,frameHeight);
  setMenuItem("file menuitem 05",fileMenuItem05,frameWidth,frameHeight);
  setMenuItem("tool menuitem 01",toolMenuItem01,frameWidth,frameHeight);
  setMenuItem("tool menuitem 02",toolMenuItem02,frameWidth,frameHeight);
  setMenuItem("tool menuitem 03",toolMenuItem03,frameWidth,frameHeight);
  setMenuItem("tool menuitem 04",toolMenuItem04,frameWidth,frameHeight);
  setMenuItem("tool menuitem 05",toolMenuItem05,frameWidth,frameHeight);
  setMenuItem("help menuitem 01",helpMenuItem01,frameWidth,frameHeight);
  setMenuItem("help menuitem 02",helpMenuItem02,frameWidth,frameHeight);
  setMenuItem("help menuitem 03",helpMenuItem03,frameWidth,frameHeight);
  setMenuItem("help menuitem 04",helpMenuItem04,frameWidth,frameHeight);
  setMenuItem("help menuitem 05",helpMenuItem05,frameWidth,frameHeight);
  setPanel("data area",jPanel19,frameWidth,frameHeight);
  setPanel("chart area",jPanel18,frameWidth,frameHeight);
  setScrollPane("table area",jScrollPane3,frameWidth,frameHeight);
  setScrollPane("station list area",jScrollPane2,frameWidth,frameHeight);
  setLabel("light label area",jLabel45,frameWidth,frameHeight);
  setPanel("light area",lightPanel2,frameWidth,frameHeight);
  setLabel("time area",jLabel13,frameWidth,frameHeight);
  setPanel("chart option area",chartOptionPanel,frameWidth,frameHeight);
  setDataArea(frameWidth,frameHeight);
}
   public Font getFont(Font defaultFont,int defaultFontSize,String fontname,String fontsize,boolean bold,boolean italy){
     return new Font((fontname.length()>0? fontname:defaultFont.getName()),(bold? (italy? (Font.BOLD | Font.ITALIC):Font.BOLD):(italy? Font.ITALIC:defaultFont.getStyle())),(wn.isNumeric(fontsize) && Integer.parseInt(fontsize)>0 ? Integer.parseInt(fontsize):defaultFontSize));
   }
  void setMenuItem(String key,JMenuItem menuItem,int frameWidth,int frameHeight){
    String info[];
    if(currentUI.get(key)!=null){
      info=ylib.csvlinetoarray((String)currentUI.get(key));
      if(info.length>2 && info[2].equalsIgnoreCase("s"))   {
        if(info[1].length()>0) menuItem.setText(info[1]);
        menuItem.setVisible(true);
      } else    menuItem.setVisible(false);
    }  else if(currentUI.size()>0) sysLog("Warning: menuitem key '"+key+"' not found.");
  }
  void setScrollPane(String key,JScrollPane scrollPane,int frameWidth,int frameHeight){
      String info[];
  if(currentUI.get(key)!=null){
    info=ylib.csvlinetoarray((String)currentUI.get(key));
    if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        scrollPane.setVisible(true);
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight));
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        scrollPane.setBounds(x, y,width,height);

    } else scrollPane.setVisible(false);
  }  else if(currentUI.size()>0) sysLog("Warning: scrollpane key '"+key+"' not found.");
  }
  void setPanel(String key,JPanel panel,int frameWidth,int frameHeight){
      String info[];
        if(currentUI.get(key)!=null){
    info=ylib.csvlinetoarray((String)currentUI.get(key));
    if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        panel.setVisible(true);
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight));
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        panel.setBounds(x, y,width,height);
        panel.setBackground((info.length>7 && info[7].length()>0 && wn.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):panel.getBackground());
        panel.setForeground((info.length>10 && info[10].length()>0 && wn.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):panel.getForeground());
    } else panel.setVisible(false);
  } else if(currentUI.size()>0) sysLog("Warning: panel key '"+key+"' not found.");
  }
   void setButton(String key,JButton button,int frameWidth,int frameHeight){
     String info[];
       if(currentUI.get(key)!=null){
       info=ylib.csvlinetoarray((String)currentUI.get(key));
       if(info.length>2 && info[2].equalsIgnoreCase("s")){
        int x=0,y=0,width=0,height=0;
        button.setVisible(true);
        if(info.length>1 && info[1].length() >0 && !(info[0].equalsIgnoreCase("connect button") || info[0].equalsIgnoreCase("start button"))) button.setText(info[1]);
        if(info.length > 3 && info[3].length()>0) x=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
        if(info.length > 4 && info[4].length()>0) y=(int)(Double.parseDouble(info[4]) * ((double)frameHeight));
        if(info.length > 5 && info[5].length()>0) width=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
        if(info.length > 6 && info[6].length()>0) height=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
        button.setBounds(x, y,width,height);
        Font font=button.getFont();
        button.setFont(getFont(font,font.getSize(),info[8],info[9],info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));

        if(info.length>7 && info[7].length()>0 && wn.isNumeric(info[7])) button.setBackground(new Color(Integer.parseInt(info[7])));
        button.setForeground((info.length>10 && info[10].length()>0 && wn.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):button.getForeground());
    } else  button.setVisible(false);
  }   else if(currentUI.size()>0) sysLog("Warning: button key '"+key+"' not found.");
   }
   void setDataArea(int frameWidth,int frameHeight){
      setLabel("da_station 01",jLabel20,frameWidth,frameHeight);
      setLabel("da_station 02",jLabel85,frameWidth,frameHeight);
      setLabel("da_station 03",jLabel168,frameWidth,frameHeight);
      setLabel("da_station 04",jLabel173,frameWidth,frameHeight);
      setLabel("da_station 05",jLabel174,frameWidth,frameHeight);
      setLabel("da_station 06",jLabel175,frameWidth,frameHeight);
      setLabel("da_station 07",jLabel176,frameWidth,frameHeight);
      setLabel("da_station 08",jLabel177,frameWidth,frameHeight);
      setLabel("da_station 09",jLabel178,frameWidth,frameHeight);
      setLabel("da_station 10",jLabel179,frameWidth,frameHeight);
      setLabel("da_station 11",jLabel180,frameWidth,frameHeight);
      setLabel("da_station 12",jLabel181,frameWidth,frameHeight);
      setLabel("da_station 13",jLabel182,frameWidth,frameHeight);
      setLabel("da_station 14",jLabel183,frameWidth,frameHeight);
      setLabel("da_station 15",jLabel184,frameWidth,frameHeight);
      setLabel("da_station 16",jLabel185,frameWidth,frameHeight);
      setLabel("da_device 01",jLabel34,frameWidth,frameHeight);
      setLabel("da_device 02",jLabel186,frameWidth,frameHeight);
      setLabel("da_device 03",jLabel187,frameWidth,frameHeight);
      setLabel("da_device 04",jLabel188,frameWidth,frameHeight);
      setLabel("da_device 05",jLabel189,frameWidth,frameHeight);
      setLabel("da_device 06",jLabel190,frameWidth,frameHeight);
      setLabel("da_device 07",jLabel191,frameWidth,frameHeight);
      setLabel("da_device 08",jLabel192,frameWidth,frameHeight);
      setLabel("da_device 09",jLabel193,frameWidth,frameHeight);
      setLabel("da_device 10",jLabel194,frameWidth,frameHeight);
      setLabel("da_device 11",jLabel195,frameWidth,frameHeight);
      setLabel("da_device 12",jLabel196,frameWidth,frameHeight);
      setLabel("da_device 13",jLabel197,frameWidth,frameHeight);
      setLabel("da_device 14",jLabel198,frameWidth,frameHeight);
      setLabel("da_device 15",jLabel199,frameWidth,frameHeight);
      setLabel("da_device 16",jLabel200,frameWidth,frameHeight);
      setLabel("da_device 17",jLabel201,frameWidth,frameHeight);
      setLabel("da_device 18",jLabel202,frameWidth,frameHeight);
      setLabel("da_device 19",jLabel203,frameWidth,frameHeight);
      setLabel("da_device 20",jLabel204,frameWidth,frameHeight);
      setLabel("da_device 21",jLabel205,frameWidth,frameHeight);
      setLabel("da_device 22",jLabel206,frameWidth,frameHeight);
      setLabel("da_device 23",jLabel207,frameWidth,frameHeight);
      setLabel("da_device 24",jLabel208,frameWidth,frameHeight);
      setLabel("da_device 25",jLabel209,frameWidth,frameHeight);
      setLabel("da_device 26",jLabel210,frameWidth,frameHeight);
      setLabel("da_device 27",jLabel211,frameWidth,frameHeight);
      setLabel("da_device 28",jLabel212,frameWidth,frameHeight);
      setLabel("da_device 29",jLabel213,frameWidth,frameHeight);
      setLabel("da_device 30",jLabel214,frameWidth,frameHeight);
      setLabel("da_device 31",jLabel215,frameWidth,frameHeight);
      setLabel("da_device 32",jLabel216,frameWidth,frameHeight);
      setLabel("da_dataname 01",jLabel35,frameWidth,frameHeight);
      setLabel("da_dataname 02",jLabel217,frameWidth,frameHeight);
      setLabel("da_dataname 03",jLabel218,frameWidth,frameHeight);
      setLabel("da_dataname 04",jLabel219,frameWidth,frameHeight);
      setLabel("da_dataname 05",jLabel220,frameWidth,frameHeight);
      setLabel("da_dataname 06",jLabel221,frameWidth,frameHeight);
      setLabel("da_dataname 07",jLabel222,frameWidth,frameHeight);
      setLabel("da_dataname 08",jLabel223,frameWidth,frameHeight);
      setLabel("da_dataname 09",jLabel224,frameWidth,frameHeight);
      setLabel("da_dataname 10",jLabel225,frameWidth,frameHeight);
      setLabel("da_dataname 11",jLabel226,frameWidth,frameHeight);
      setLabel("da_dataname 12",jLabel227,frameWidth,frameHeight);
      setLabel("da_dataname 13",jLabel228,frameWidth,frameHeight);
      setLabel("da_dataname 14",jLabel229,frameWidth,frameHeight);
      setLabel("da_dataname 15",jLabel230,frameWidth,frameHeight);
      setLabel("da_dataname 16",jLabel231,frameWidth,frameHeight);
      setLabel("da_dataname 17",jLabel232,frameWidth,frameHeight);
      setLabel("da_dataname 18",jLabel233,frameWidth,frameHeight);
      setLabel("da_dataname 19",jLabel234,frameWidth,frameHeight);
      setLabel("da_dataname 20",jLabel235,frameWidth,frameHeight);
      setLabel("da_dataname 21",jLabel236,frameWidth,frameHeight);
      setLabel("da_dataname 22",jLabel237,frameWidth,frameHeight);
      setLabel("da_dataname 23",jLabel238,frameWidth,frameHeight);
      setLabel("da_dataname 24",jLabel239,frameWidth,frameHeight);
      setLabel("da_dataname 25",jLabel240,frameWidth,frameHeight);
      setLabel("da_dataname 26",jLabel241,frameWidth,frameHeight);
      setLabel("da_dataname 27",jLabel242,frameWidth,frameHeight);
      setLabel("da_dataname 28",jLabel243,frameWidth,frameHeight);
      setLabel("da_dataname 29",jLabel244,frameWidth,frameHeight);
      setLabel("da_dataname 30",jLabel245,frameWidth,frameHeight);
      setLabel("da_dataname 31",jLabel246,frameWidth,frameHeight);
      setLabel("da_dataname 32",jLabel247,frameWidth,frameHeight);
      setLabel("da_dataname 33",jLabel248,frameWidth,frameHeight);
      setLabel("da_dataname 34",jLabel249,frameWidth,frameHeight);
      setLabel("da_dataname 35",jLabel250,frameWidth,frameHeight);
      setLabel("da_dataname 36",jLabel251,frameWidth,frameHeight);
      setLabel("da_dataname 37",jLabel252,frameWidth,frameHeight);
      setLabel("da_dataname 38",jLabel253,frameWidth,frameHeight);
      setLabel("da_dataname 39",jLabel254,frameWidth,frameHeight);
      setLabel("da_dataname 40",jLabel255,frameWidth,frameHeight);
      setLabel("da_dataname 41",jLabel256,frameWidth,frameHeight);
      setLabel("da_dataname 42",jLabel257,frameWidth,frameHeight);
      setLabel("da_dataname 43",jLabel258,frameWidth,frameHeight);
      setLabel("da_dataname 44",jLabel259,frameWidth,frameHeight);
      setLabel("da_dataname 45",jLabel260,frameWidth,frameHeight);
      setLabel("da_dataname 46",jLabel261,frameWidth,frameHeight);
      setLabel("da_dataname 47",jLabel262,frameWidth,frameHeight);
      setLabel("da_dataname 48",jLabel263,frameWidth,frameHeight);
      setLabel("da_datavalue 01",jLabel30,frameWidth,frameHeight);
      setLabel("da_datavalue 02",jLabel264,frameWidth,frameHeight);
      setLabel("da_datavalue 03",jLabel265,frameWidth,frameHeight);
      setLabel("da_datavalue 04",jLabel266,frameWidth,frameHeight);
      setLabel("da_datavalue 05",jLabel267,frameWidth,frameHeight);
      setLabel("da_datavalue 06",jLabel268,frameWidth,frameHeight);
      setLabel("da_datavalue 07",jLabel269,frameWidth,frameHeight);
      setLabel("da_datavalue 08",jLabel270,frameWidth,frameHeight);
      setLabel("da_datavalue 09",jLabel271,frameWidth,frameHeight);
      setLabel("da_datavalue 10",jLabel272,frameWidth,frameHeight);
      setLabel("da_datavalue 11",jLabel273,frameWidth,frameHeight);
      setLabel("da_datavalue 12",jLabel274,frameWidth,frameHeight);
      setLabel("da_datavalue 13",jLabel275,frameWidth,frameHeight);
      setLabel("da_datavalue 14",jLabel276,frameWidth,frameHeight);
      setLabel("da_datavalue 15",jLabel277,frameWidth,frameHeight);
      setLabel("da_datavalue 16",jLabel278,frameWidth,frameHeight);
      setLabel("da_datavalue 17",jLabel279,frameWidth,frameHeight);
      setLabel("da_datavalue 18",jLabel280,frameWidth,frameHeight);
      setLabel("da_datavalue 19",jLabel281,frameWidth,frameHeight);
      setLabel("da_datavalue 20",jLabel282,frameWidth,frameHeight);
      setLabel("da_datavalue 21",jLabel283,frameWidth,frameHeight);
      setLabel("da_datavalue 22",jLabel284,frameWidth,frameHeight);
      setLabel("da_datavalue 23",jLabel285,frameWidth,frameHeight);
      setLabel("da_datavalue 24",jLabel286,frameWidth,frameHeight);
      setLabel("da_datavalue 25",jLabel287,frameWidth,frameHeight);
      setLabel("da_datavalue 26",jLabel288,frameWidth,frameHeight);
      setLabel("da_datavalue 27",jLabel289,frameWidth,frameHeight);
      setLabel("da_datavalue 28",jLabel290,frameWidth,frameHeight);
      setLabel("da_datavalue 29",jLabel291,frameWidth,frameHeight);
      setLabel("da_datavalue 30",jLabel292,frameWidth,frameHeight);
      setLabel("da_datavalue 31",jLabel293,frameWidth,frameHeight);
      setLabel("da_datavalue 32",jLabel294,frameWidth,frameHeight);
      setLabel("da_datavalue 33",jLabel295,frameWidth,frameHeight);
      setLabel("da_datavalue 34",jLabel296,frameWidth,frameHeight);
      setLabel("da_datavalue 35",jLabel297,frameWidth,frameHeight);
      setLabel("da_datavalue 36",jLabel298,frameWidth,frameHeight);
      setLabel("da_datavalue 37",jLabel299,frameWidth,frameHeight);
      setLabel("da_datavalue 38",jLabel300,frameWidth,frameHeight);
      setLabel("da_datavalue 39",jLabel301,frameWidth,frameHeight);
      setLabel("da_datavalue 40",jLabel302,frameWidth,frameHeight);
      setLabel("da_datavalue 41",jLabel303,frameWidth,frameHeight);
      setLabel("da_datavalue 42",jLabel304,frameWidth,frameHeight);
      setLabel("da_datavalue 43",jLabel305,frameWidth,frameHeight);
      setLabel("da_datavalue 44",jLabel306,frameWidth,frameHeight);
      setLabel("da_datavalue 45",jLabel307,frameWidth,frameHeight);
      setLabel("da_datavalue 46",jLabel308,frameWidth,frameHeight);
      setLabel("da_datavalue 47",jLabel309,frameWidth,frameHeight);
      setLabel("da_datavalue 48",jLabel310,frameWidth,frameHeight);
   }
   void setLabel(String key,JLabel label,int frameWidth,int frameHeight){
     String info[];
     if(currentUI.get(key)!=null){
         info=ylib.csvlinetoarray((String)currentUI.get(key));
         if(info.length>2 && info[2].equalsIgnoreCase("s")){
           int x2=0,y2=0,width2=0,height2=0;
           label.setVisible(true);
           if(info.length > 3 && info[3].length()>0) x2=(int)(Double.parseDouble(info[3]) * ((double)frameWidth));
           if(info.length > 4 && info[4].length()>0) y2=(int)(Double.parseDouble(info[4]) * ((double)frameHeight));
           if(info.length > 5 && info[5].length()>0) width2=(int)(Double.parseDouble(info[5]) * ((double)frameWidth));
           if(info.length > 6 && info[6].length()>0) height2=(int)(Double.parseDouble(info[6]) * ((double)frameHeight));
           label.setBounds(x2, y2,width2,height2);
           Font font=label.getFont();
           if(info[1].trim().length()>0) label.setText(info[1]);
           label.setFont(getFont(font,font.getSize(),info[8],info[9],info[11].equalsIgnoreCase("b"),info[12].equalsIgnoreCase("i")));
           label.setBackground((info.length>7 && info[7].length()>0 && wn.isNumeric(info[7]))? new Color(Integer.parseInt(info[7])):label.getBackground());
           label.setForeground((info.length>10 && info[10].length()>0 && wn.isNumeric(info[10]))? new Color(Integer.parseInt(info[10])):label.getForeground());
         } else label.setVisible(false);
       } else if(currentUI.size()>0) sysLog("Warning: label key '"+key+"' not found.");
   }
  void removeFromCoorDevices(String deviceAddr, int from) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("removeFromCoorDevices(),deviceAddr=" + deviceAddr + ", from=" + from);
    }
    String coor = "", stationName = "", id = "";
    boolean found = false;
    Iterator it = coorDevices.keySet().iterator();
    for (; it.hasNext();) {
      String tmpCoor = (String) it.next();
      TreeMap tm = (TreeMap) coorDevices.get(tmpCoor);
      Iterator it2 = tm.keySet().iterator();
      for (; it2.hasNext();) {
        String tmpId = (String) it2.next();
        if (deviceAddr.equals((String) tm.get(tmpId))) {
          found = true;
          coor = tmpCoor;
          id = tmpId;
          break;
        }
      }
    }
    if (found) {
      TreeMap tm = (TreeMap) coorDevices.get(coor);
      tm.remove(id);
      if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
        sysLog("remove " + deviceAddr + " from " + ports.get((String) coorToPorts.get(coor)) + "-" + id + ".");
      }
      if (tm.size() < 1) {
        coorDevices.remove(coor);
      } else {
        coorDevices.put(coor, tm);
      }
    }
  }

  double getDouble3_c(String input, String previous) {

    int v1 = Integer.parseInt(input.substring(0, 2), 16);
    double rtn = 0.0;
    try {
      if (v1 > 127) {
        v1 = v1 - 128;
        String h = Integer.toHexString(v1).toUpperCase();
        switch (h.length()) {
          case 1:
            h = "0" + h;
            break;
          case 2:

            break;
          default:
            h = h.substring(h.length() - 2, h.length());
            break;
        }
        input = h + "." + input.substring(3, 5) + input.substring(6, 8);
        if (wn.isNumeric(input)) {
          rtn = Double.parseDouble(input) * (-1.0);
        } else {
          System.out.println("NumberFormatException, input= " + input);
          rtn = Double.parseDouble(previous);
        }
      } else {
        input = input.substring(0, 2) + "." + input.substring(3, 5) + input.substring(6, 8);
        if (wn.isNumeric(input)) {
          rtn = Double.parseDouble(input);
        } else {
          System.out.println("NumberFormatException, input= " + input);
          rtn = Double.parseDouble(previous);
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("NumberFormatException, input= " + input);
      rtn = Double.parseDouble(previous);
    }
    return rtn;
  }

  double getDouble3_m(String input, String previous) {

    int v1 = Integer.parseInt(input.substring(0, 2), 16);
    double rtn = 0.0;
    try {
      if (v1 > 127) {
        v1 = v1 - 128;
        String h = Integer.toHexString(v1).toUpperCase();
        switch (h.length()) {
          case 1:
            h = "0" + h;
            break;
          case 2:

            break;
          default:
            h = h.substring(h.length() - 2, h.length());
            break;
        }
        input = h + "." + input.substring(3, 5) + input.substring(6, 8);
        if (wn.isNumeric(input)) {
          rtn = Double.parseDouble(input) * (-1.0);
        } else {
          System.out.println("NumberFormatException, input= " + input);
          rtn = Double.parseDouble(previous);
        }
      } else {
        input = input.substring(0, 2) + "." + input.substring(3, 5) + input.substring(6, 8);
        if (wn.isNumeric(input)) {
          rtn = Double.parseDouble(input);
        } else {
          System.out.println("NumberFormatException, input= " + input);
          rtn = Double.parseDouble(previous);
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("NumberFormatException, input= " + input);
      rtn = Double.parseDouble(previous);
    }
    return rtn;
  }

  

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stationList = new javax.swing.JList(stationListModel);
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jLabel238 = new javax.swing.JLabel();
        jLabel239 = new javax.swing.JLabel();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        jLabel242 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        jLabel245 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel247 = new javax.swing.JLabel();
        jLabel248 = new javax.swing.JLabel();
        jLabel249 = new javax.swing.JLabel();
        jLabel250 = new javax.swing.JLabel();
        jLabel251 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel();
        jLabel253 = new javax.swing.JLabel();
        jLabel254 = new javax.swing.JLabel();
        jLabel255 = new javax.swing.JLabel();
        jLabel256 = new javax.swing.JLabel();
        jLabel257 = new javax.swing.JLabel();
        jLabel258 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        jLabel262 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        jLabel264 = new javax.swing.JLabel();
        jLabel265 = new javax.swing.JLabel();
        jLabel266 = new javax.swing.JLabel();
        jLabel267 = new javax.swing.JLabel();
        jLabel268 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jLabel270 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        jLabel272 = new javax.swing.JLabel();
        jLabel273 = new javax.swing.JLabel();
        jLabel274 = new javax.swing.JLabel();
        jLabel275 = new javax.swing.JLabel();
        jLabel276 = new javax.swing.JLabel();
        jLabel277 = new javax.swing.JLabel();
        jLabel278 = new javax.swing.JLabel();
        jLabel279 = new javax.swing.JLabel();
        jLabel280 = new javax.swing.JLabel();
        jLabel281 = new javax.swing.JLabel();
        jLabel282 = new javax.swing.JLabel();
        jLabel283 = new javax.swing.JLabel();
        jLabel284 = new javax.swing.JLabel();
        jLabel285 = new javax.swing.JLabel();
        jLabel286 = new javax.swing.JLabel();
        jLabel287 = new javax.swing.JLabel();
        jLabel288 = new javax.swing.JLabel();
        jLabel289 = new javax.swing.JLabel();
        jLabel290 = new javax.swing.JLabel();
        jLabel291 = new javax.swing.JLabel();
        jLabel292 = new javax.swing.JLabel();
        jLabel293 = new javax.swing.JLabel();
        jLabel294 = new javax.swing.JLabel();
        jLabel295 = new javax.swing.JLabel();
        jLabel296 = new javax.swing.JLabel();
        jLabel297 = new javax.swing.JLabel();
        jLabel298 = new javax.swing.JLabel();
        jLabel299 = new javax.swing.JLabel();
        jLabel300 = new javax.swing.JLabel();
        jLabel301 = new javax.swing.JLabel();
        jLabel302 = new javax.swing.JLabel();
        jLabel303 = new javax.swing.JLabel();
        jLabel304 = new javax.swing.JLabel();
        jLabel305 = new javax.swing.JLabel();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        jLabel308 = new javax.swing.JLabel();
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        button02 = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        chartOptionPanel = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        cbAutoAdjustY = new javax.swing.JCheckBox();
        jPanel45 = new javax.swing.JPanel();
        cbZero = new javax.swing.JCheckBox();
        cbRemark = new javax.swing.JCheckBox();
        jPanel46 = new javax.swing.JPanel();
        showAlarmRB = new javax.swing.JCheckBox();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        lightPanel2 = new ci.LightPanel();
        button03 = new javax.swing.JButton();
        button04 = new javax.swing.JButton();
        button05 = new javax.swing.JButton();
        button06 = new javax.swing.JButton();
        button07 = new javax.swing.JButton();
        button08 = new javax.swing.JButton();
        button09 = new javax.swing.JButton();
        button10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton23 = new javax.swing.JButton();
        btnEditSrc = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton27 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel38 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox();
        jLabel61 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox();
        jLabel63 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jComboBox23 = new javax.swing.JComboBox();
        jLabel66 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jComboBox24 = new javax.swing.JComboBox();
        jLabel68 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox();
        jLabel70 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox();
        jLabel50 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel51 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel65 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel71 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox48 = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel42 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jPanel108 = new javax.swing.JPanel();
        jLabel126 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jCheckBox10 = new javax.swing.JCheckBox();
        jPanel23 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        CBUseEngineerUnit = new javax.swing.JCheckBox();
        jButton17 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel44 = new javax.swing.JPanel();
        btnLogoutAdmin = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel54 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jPanel55 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        btnTestEMail = new javax.swing.JButton();
        jPanel58 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPasswordField7 = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnTestSMS = new javax.swing.JButton();
        jPanel56 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel53 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField5 = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        FTPPanel = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jCheckBox9 = new javax.swing.JCheckBox();
        jPanel59 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jPanel60 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jPanel61 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPasswordField6 = new javax.swing.JPasswordField();
        jPanel62 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jPanel63 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        onlyReceiveCB = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jPanel67 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPasswordField8 = new javax.swing.JPasswordField();
        jPanel68 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPasswordField9 = new javax.swing.JPasswordField();
        jButton24 = new javax.swing.JButton();
        UIPanel = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel36 = new javax.swing.JPanel();
        jPanel140 = new javax.swing.JPanel();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jPanel144 = new javax.swing.JPanel();
        jPanel141 = new javax.swing.JPanel();
        jComboBox36 = new javax.swing.JComboBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jPanel143 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jComboBox37 = new javax.swing.JComboBox();
        jLabel72 = new javax.swing.JLabel();
        jComboBox53 = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        jTextField73 = new javax.swing.JTextField();
        jLabel311 = new javax.swing.JLabel();
        jComboBox55 = new javax.swing.JComboBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jPanel145 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jComboBox54 = new javax.swing.JComboBox();
        jPanel146 = new javax.swing.JPanel();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jPanel147 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jCheckBox43 = new javax.swing.JCheckBox();
        jLabel105 = new javax.swing.JLabel();
        jTextField74 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jTextField75 = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jTextField76 = new javax.swing.JTextField();
        jLabel156 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jTextField77 = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel123 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        byStation = new javax.swing.JRadioButton();
        byDevice = new javax.swing.JRadioButton();
        byModel = new javax.swing.JRadioButton();
        byDataName = new javax.swing.JRadioButton();
        byChartGroup = new javax.swing.JRadioButton();
        chartGroupCB = new javax.swing.JComboBox();
        jPanel109 = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        jPanel115 = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        showCB = new javax.swing.JCheckBox();
        show16RB = new javax.swing.JRadioButton();
        showStrRB = new javax.swing.JRadioButton();
        crnlCB = new javax.swing.JCheckBox();
        showTimeCB = new javax.swing.JCheckBox();
        showSrcCB = new javax.swing.JCheckBox();
        showSysMsgCB = new javax.swing.JCheckBox();
        saveFileCB = new javax.swing.JCheckBox();
        clearShowBtn = new javax.swing.JButton();
        jPanel116 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        receiveTP = new javax.swing.JTextPane(styleDoc);
        jScrollPane12 = new javax.swing.JScrollPane();
        receiveList = new javax.swing.JList(receiveListModel);
        jPanel114 = new javax.swing.JPanel();
        jPanel117 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        sendTA = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        sendList = new javax.swing.JList(sendListModel);
        jPanel118 = new javax.swing.JPanel();
        jPanel119 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        send16RB = new javax.swing.JRadioButton();
        sendStrRB = new javax.swing.JRadioButton();
        chkSumCB = new javax.swing.JCheckBox();
        chkSumCBB = new javax.swing.JComboBox();
        jPanel120 = new javax.swing.JPanel();
        continueSendCB = new javax.swing.JCheckBox();
        jLabel152 = new javax.swing.JLabel();
        sendIntervalTF = new javax.swing.JTextField();
        jLabel153 = new javax.swing.JLabel();
        sendBtn = new javax.swing.JButton();
        stopContinueSendBtn = new javax.swing.JButton();
        clearSendBtn = new javax.swing.JButton();
        NodeMgntPanel = new javax.swing.JPanel();
        jButton41 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        jList9 = new javax.swing.JList(listModel9);
        jScrollPane16 = new javax.swing.JScrollPane();
        jList10 = new javax.swing.JList();
        jPanel49 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        eventList = new javax.swing.JList(eventListModel);
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        conditionList2 = new javax.swing.JList(conditionListModel2);
        jScrollPane18 = new javax.swing.JScrollPane();
        actionList2 = new javax.swing.JList(actionListModel2);
        jButton28 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jPanel70 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        actionList = new javax.swing.JList(actionListModel);
        jButton25 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        updateActionBtn = new javax.swing.JButton();
        jPanel76 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox();
        jPanel77 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox();
        jPanel79 = new javax.swing.JPanel();
        jPanel83 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jComboBox28 = new javax.swing.JComboBox();
        jComboBox29 = new javax.swing.JComboBox();
        jLabel79 = new javax.swing.JLabel();
        jTextField56 = new javax.swing.JTextField();
        jPanel86 = new javax.swing.JPanel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jTextField27 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jPanel87 = new javax.swing.JPanel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jTextField30 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jPanel88 = new javax.swing.JPanel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jPanel104 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel75 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jCheckBox34 = new javax.swing.JCheckBox();
        jTextField16 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jCheckBox27 = new javax.swing.JCheckBox();
        jPanel128 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jCheckBox28 = new javax.swing.JCheckBox();
        jTextField63 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jPanel78 = new javax.swing.JPanel();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jPanel94 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jComboBox32 = new javax.swing.JComboBox();
        jComboBox41 = new javax.swing.JComboBox();
        jLabel111 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jPanel121 = new javax.swing.JPanel();
        jCheckBox33 = new javax.swing.JCheckBox();
        jLabel112 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jCheckBox36 = new javax.swing.JCheckBox();
        jTextField59 = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        jTextField60 = new javax.swing.JTextField();
        jPanel137 = new javax.swing.JPanel();
        jCheckBox39 = new javax.swing.JCheckBox();
        jLabel164 = new javax.swing.JLabel();
        jTextField67 = new javax.swing.JTextField();
        jCheckBox40 = new javax.swing.JCheckBox();
        jTextField68 = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jTextField69 = new javax.swing.JTextField();
        jPanel138 = new javax.swing.JPanel();
        jCheckBox41 = new javax.swing.JCheckBox();
        jLabel166 = new javax.swing.JLabel();
        jTextField70 = new javax.swing.JTextField();
        jCheckBox42 = new javax.swing.JCheckBox();
        jTextField71 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        jTextField72 = new javax.swing.JTextField();
        jPanel84 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        jTextField53 = new javax.swing.JTextField();
        jPanel122 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        jComboBox44 = new javax.swing.JComboBox();
        jPanel132 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        jComboBox45 = new javax.swing.JComboBox();
        jPanel136 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jComboBox49 = new javax.swing.JComboBox();
        jPanel85 = new javax.swing.JPanel();
        jPanel89 = new javax.swing.JPanel();
        jComboBox30 = new javax.swing.JComboBox();
        jTextField31 = new javax.swing.JTextField();
        jPanel90 = new javax.swing.JPanel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jComboBox31 = new javax.swing.JComboBox();
        jPanel91 = new javax.swing.JPanel();
        jCheckBox11 = new javax.swing.JCheckBox();
        jTextField32 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jPanel129 = new javax.swing.JPanel();
        jCheckBox29 = new javax.swing.JCheckBox();
        jTextField64 = new javax.swing.JTextField();
        jPanel126 = new javax.swing.JPanel();
        jPanel127 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jPanel139 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField54 = new javax.swing.JTextField();
        jPanel69 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        conditionList = new javax.swing.JList(conditionListModel);
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        updateConditionBtn = new javax.swing.JButton();
        jPanel72 = new javax.swing.JPanel();
        jPanel80 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox();
        jComboBox26 = new javax.swing.JComboBox();
        jLabel130 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jPanel81 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jCheckBox35 = new javax.swing.JCheckBox();
        jTextField4 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel82 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jCheckBox30 = new javax.swing.JCheckBox();
        jComboBox27 = new javax.swing.JComboBox();
        jTextField14 = new javax.swing.JTextField();
        jPanel130 = new javax.swing.JPanel();
        jCheckBox31 = new javax.swing.JCheckBox();
        jComboBox39 = new javax.swing.JComboBox();
        jTextField65 = new javax.swing.JTextField();
        jPanel131 = new javax.swing.JPanel();
        jCheckBox32 = new javax.swing.JCheckBox();
        jComboBox43 = new javax.swing.JComboBox();
        jTextField66 = new javax.swing.JTextField();
        jPanel73 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox();
        jPanel74 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jPanel124 = new javax.swing.JPanel();
        jPanel125 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jTextField55 = new javax.swing.JTextField();
        jPanel133 = new javax.swing.JPanel();
        jLabel160 = new javax.swing.JLabel();
        jComboBox46 = new javax.swing.JComboBox();
        jPanel134 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jComboBox47 = new javax.swing.JComboBox();
        jPanel135 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        jComboBox48 = new javax.swing.JComboBox();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        chartList = new javax.swing.JList(chartListModel);
        jLabel82 = new javax.swing.JLabel();
        jPanel93 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jLabel146 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jComboBox42 = new javax.swing.JComboBox();
        jPanel96 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jCheckBox12 = new javax.swing.JCheckBox();
        jPanel97 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField35 = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jTextField36 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jPanel98 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jPanel99 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jPanel100 = new javax.swing.JPanel();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jPanel101 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jPanel103 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jTextField47 = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jTextField49 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jTextField50 = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jPanel106 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox();
        jLabel138 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jPanel111 = new javax.swing.JPanel();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jTextField52 = new javax.swing.JTextField();
        jPanel142 = new javax.swing.JPanel();
        jPanel112 = new javax.swing.JPanel();
        jCheckBox23 = new javax.swing.JCheckBox();
        jTextField43 = new javax.swing.JTextField();
        jPanel148 = new javax.swing.JPanel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jPanel149 = new javax.swing.JPanel();
        jCheckBox14 = new javax.swing.JCheckBox();
        jPanel51 = new javax.swing.JPanel();
        jPanel92 = new javax.swing.JPanel();
        jLabel169 = new javax.swing.JLabel();
        jComboBox40 = new javax.swing.JComboBox();
        jLabel170 = new javax.swing.JLabel();
        jComboBox50 = new javax.swing.JComboBox();
        jLabel171 = new javax.swing.JLabel();
        jComboBox51 = new javax.swing.JComboBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        curveList = new javax.swing.JList(curveListModel);
        jLabel109 = new javax.swing.JLabel();
        jPanel95 = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jPanel102 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jComboBox33 = new javax.swing.JComboBox();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jPanel105 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jComboBox34 = new javax.swing.JComboBox();
        jLabel89 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jComboBox52 = new javax.swing.JComboBox();
        jPanel107 = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jComboBox38 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        fileUpLoadMenuItem = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        fileMenuItem01 = new javax.swing.JMenuItem();
        fileMenuItem02 = new javax.swing.JMenuItem();
        fileMenuItem03 = new javax.swing.JMenuItem();
        fileMenuItem04 = new javax.swing.JMenuItem();
        fileMenuItem05 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        toolMenuItem01 = new javax.swing.JMenuItem();
        toolMenuItem02 = new javax.swing.JMenuItem();
        toolMenuItem03 = new javax.swing.JMenuItem();
        toolMenuItem04 = new javax.swing.JMenuItem();
        toolMenuItem05 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        helpMenuItem01 = new javax.swing.JMenuItem();
        helpMenuItem02 = new javax.swing.JMenuItem();
        helpMenuItem03 = new javax.swing.JMenuItem();
        helpMenuItem04 = new javax.swing.JMenuItem();
        helpMenuItem05 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ci/Bundle"); 
        setTitle(bundle.getString("CrInstrument.title")); 
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(jPanel1.getFont());
        jPanel1.setLayout(null);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("CrInstrument.jScrollPane2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)); 
        jScrollPane2.setFont(jScrollPane2.getFont());

        stationList.setFont(stationList.getFont());
        stationList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stationListMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(stationList);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(0, 10, 160, 180);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("CrInstrument.jScrollPane3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)); 
        jScrollPane3.setFont(jScrollPane3.getFont());

        jTable2.setFont(jTable2.getFont());
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Device Name", "Model", "SN", "Data Name", "Value", "Alert"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(153, 153, 153));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(170, 10, 380, 300);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("CrInstrument.jPanel18.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)); 
        jPanel18.setFont(jPanel18.getFont());
        jPanel18.setLayout(null);
        jPanel1.add(jPanel18);
        jPanel18.setBounds(560, 10, 440, 460);

        jPanel19.setBackground(new java.awt.Color(0, 0, 0));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), bundle.getString("CrInstrument.jPanel19.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(255, 255, 255))); 
        jPanel19.setLayout(null);

        jLabel30.setFont(jLabel30.getFont().deriveFont(jLabel30.getFont().getStyle() | java.awt.Font.BOLD, jLabel30.getFont().getSize()+36));
        jLabel30.setForeground(new java.awt.Color(255, 0, 51));
        jLabel30.setText(bundle.getString("CrInstrument.jLabel30.text")); 
        jPanel19.add(jLabel30);
        jLabel30.setBounds(60, 150, 300, 60);

        jLabel34.setFont(jLabel34.getFont().deriveFont(jLabel34.getFont().getStyle() | java.awt.Font.BOLD, jLabel34.getFont().getSize()+6));
        jLabel34.setForeground(new java.awt.Color(0, 255, 102));
        jLabel34.setText(bundle.getString("CrInstrument.jLabel34.text")); 
        jPanel19.add(jLabel34);
        jLabel34.setBounds(20, 60, 320, 30);

        jLabel35.setFont(jLabel35.getFont().deriveFont(jLabel35.getFont().getStyle() | java.awt.Font.BOLD, jLabel35.getFont().getSize()+6));
        jLabel35.setForeground(new java.awt.Color(255, 255, 51));
        jLabel35.setText(bundle.getString("CrInstrument.jLabel35.text")); 
        jPanel19.add(jLabel35);
        jLabel35.setBounds(20, 110, 320, 30);

        jLabel20.setFont(jLabel20.getFont().deriveFont(jLabel20.getFont().getStyle() | java.awt.Font.BOLD, jLabel20.getFont().getSize()+6));
        jLabel20.setForeground(new java.awt.Color(153, 255, 255));
        jLabel20.setText(bundle.getString("CrInstrument.jLabel20.text")); 
        jPanel19.add(jLabel20);
        jLabel20.setBounds(20, 30, 280, 20);

        jLabel85.setFont(jLabel85.getFont().deriveFont(jLabel85.getFont().getStyle() | java.awt.Font.BOLD, jLabel85.getFont().getSize()+6));
        jLabel85.setForeground(new java.awt.Color(153, 255, 255));
        jLabel85.setText(bundle.getString("CrInstrument.jLabel85.text")); 
        jPanel19.add(jLabel85);
        jLabel85.setBounds(20, 30, 280, 20);

        jLabel168.setFont(jLabel168.getFont().deriveFont(jLabel168.getFont().getStyle() | java.awt.Font.BOLD, jLabel168.getFont().getSize()+6));
        jLabel168.setForeground(new java.awt.Color(153, 255, 255));
        jLabel168.setText(bundle.getString("CrInstrument.jLabel168.text")); 
        jPanel19.add(jLabel168);
        jLabel168.setBounds(20, 30, 280, 20);

        jLabel173.setFont(jLabel173.getFont().deriveFont(jLabel173.getFont().getStyle() | java.awt.Font.BOLD, jLabel173.getFont().getSize()+6));
        jLabel173.setForeground(new java.awt.Color(153, 255, 255));
        jLabel173.setText(bundle.getString("CrInstrument.jLabel173.text")); 
        jPanel19.add(jLabel173);
        jLabel173.setBounds(20, 30, 280, 20);

        jLabel174.setFont(jLabel174.getFont().deriveFont(jLabel174.getFont().getStyle() | java.awt.Font.BOLD, jLabel174.getFont().getSize()+6));
        jLabel174.setForeground(new java.awt.Color(153, 255, 255));
        jLabel174.setText(bundle.getString("CrInstrument.jLabel174.text")); 
        jPanel19.add(jLabel174);
        jLabel174.setBounds(20, 30, 280, 20);

        jLabel175.setFont(jLabel175.getFont().deriveFont(jLabel175.getFont().getStyle() | java.awt.Font.BOLD, jLabel175.getFont().getSize()+6));
        jLabel175.setForeground(new java.awt.Color(153, 255, 255));
        jLabel175.setText(bundle.getString("CrInstrument.jLabel175.text")); 
        jPanel19.add(jLabel175);
        jLabel175.setBounds(20, 30, 280, 20);

        jLabel176.setFont(jLabel176.getFont().deriveFont(jLabel176.getFont().getStyle() | java.awt.Font.BOLD, jLabel176.getFont().getSize()+6));
        jLabel176.setForeground(new java.awt.Color(153, 255, 255));
        jLabel176.setText(bundle.getString("CrInstrument.jLabel176.text")); 
        jPanel19.add(jLabel176);
        jLabel176.setBounds(20, 30, 280, 20);

        jLabel177.setFont(jLabel177.getFont().deriveFont(jLabel177.getFont().getStyle() | java.awt.Font.BOLD, jLabel177.getFont().getSize()+6));
        jLabel177.setForeground(new java.awt.Color(153, 255, 255));
        jLabel177.setText(bundle.getString("CrInstrument.jLabel177.text")); 
        jPanel19.add(jLabel177);
        jLabel177.setBounds(20, 30, 280, 20);

        jLabel178.setFont(jLabel178.getFont().deriveFont(jLabel178.getFont().getStyle() | java.awt.Font.BOLD, jLabel178.getFont().getSize()+6));
        jLabel178.setForeground(new java.awt.Color(153, 255, 255));
        jLabel178.setText(bundle.getString("CrInstrument.jLabel178.text")); 
        jPanel19.add(jLabel178);
        jLabel178.setBounds(20, 30, 280, 20);

        jLabel179.setFont(jLabel179.getFont().deriveFont(jLabel179.getFont().getStyle() | java.awt.Font.BOLD, jLabel179.getFont().getSize()+6));
        jLabel179.setForeground(new java.awt.Color(153, 255, 255));
        jLabel179.setText(bundle.getString("CrInstrument.jLabel179.text")); 
        jPanel19.add(jLabel179);
        jLabel179.setBounds(20, 30, 280, 20);

        jLabel180.setFont(jLabel180.getFont().deriveFont(jLabel180.getFont().getStyle() | java.awt.Font.BOLD, jLabel180.getFont().getSize()+6));
        jLabel180.setForeground(new java.awt.Color(153, 255, 255));
        jLabel180.setText(bundle.getString("CrInstrument.jLabel180.text")); 
        jPanel19.add(jLabel180);
        jLabel180.setBounds(20, 30, 280, 20);

        jLabel181.setFont(jLabel181.getFont().deriveFont(jLabel181.getFont().getStyle() | java.awt.Font.BOLD, jLabel181.getFont().getSize()+6));
        jLabel181.setForeground(new java.awt.Color(153, 255, 255));
        jLabel181.setText(bundle.getString("CrInstrument.jLabel181.text")); 
        jPanel19.add(jLabel181);
        jLabel181.setBounds(20, 30, 280, 20);

        jLabel182.setFont(jLabel182.getFont().deriveFont(jLabel182.getFont().getStyle() | java.awt.Font.BOLD, jLabel182.getFont().getSize()+6));
        jLabel182.setForeground(new java.awt.Color(153, 255, 255));
        jLabel182.setText(bundle.getString("CrInstrument.jLabel182.text")); 
        jPanel19.add(jLabel182);
        jLabel182.setBounds(20, 30, 280, 20);

        jLabel183.setFont(jLabel183.getFont().deriveFont(jLabel183.getFont().getStyle() | java.awt.Font.BOLD, jLabel183.getFont().getSize()+6));
        jLabel183.setForeground(new java.awt.Color(153, 255, 255));
        jLabel183.setText(bundle.getString("CrInstrument.jLabel183.text")); 
        jPanel19.add(jLabel183);
        jLabel183.setBounds(20, 30, 280, 20);

        jLabel184.setFont(jLabel184.getFont().deriveFont(jLabel184.getFont().getStyle() | java.awt.Font.BOLD, jLabel184.getFont().getSize()+6));
        jLabel184.setForeground(new java.awt.Color(153, 255, 255));
        jLabel184.setText(bundle.getString("CrInstrument.jLabel184.text")); 
        jPanel19.add(jLabel184);
        jLabel184.setBounds(20, 30, 280, 20);

        jLabel185.setFont(jLabel185.getFont().deriveFont(jLabel185.getFont().getStyle() | java.awt.Font.BOLD, jLabel185.getFont().getSize()+6));
        jLabel185.setForeground(new java.awt.Color(153, 255, 255));
        jLabel185.setText(bundle.getString("CrInstrument.jLabel185.text")); 
        jPanel19.add(jLabel185);
        jLabel185.setBounds(20, 30, 280, 20);

        jLabel186.setFont(jLabel186.getFont().deriveFont(jLabel186.getFont().getStyle() | java.awt.Font.BOLD, jLabel186.getFont().getSize()+6));
        jLabel186.setForeground(new java.awt.Color(0, 255, 102));
        jLabel186.setText(bundle.getString("CrInstrument.jLabel186.text")); 
        jPanel19.add(jLabel186);
        jLabel186.setBounds(20, 60, 320, 30);

        jLabel187.setFont(jLabel187.getFont().deriveFont(jLabel187.getFont().getStyle() | java.awt.Font.BOLD, jLabel187.getFont().getSize()+6));
        jLabel187.setForeground(new java.awt.Color(0, 255, 102));
        jLabel187.setText(bundle.getString("CrInstrument.jLabel187.text")); 
        jPanel19.add(jLabel187);
        jLabel187.setBounds(20, 60, 320, 30);

        jLabel188.setFont(jLabel188.getFont().deriveFont(jLabel188.getFont().getStyle() | java.awt.Font.BOLD, jLabel188.getFont().getSize()+6));
        jLabel188.setForeground(new java.awt.Color(0, 255, 102));
        jLabel188.setText(bundle.getString("CrInstrument.jLabel188.text")); 
        jPanel19.add(jLabel188);
        jLabel188.setBounds(20, 60, 320, 30);

        jLabel189.setFont(jLabel189.getFont().deriveFont(jLabel189.getFont().getStyle() | java.awt.Font.BOLD, jLabel189.getFont().getSize()+6));
        jLabel189.setForeground(new java.awt.Color(0, 255, 102));
        jLabel189.setText(bundle.getString("CrInstrument.jLabel189.text")); 
        jPanel19.add(jLabel189);
        jLabel189.setBounds(20, 60, 320, 30);

        jLabel190.setFont(jLabel190.getFont().deriveFont(jLabel190.getFont().getStyle() | java.awt.Font.BOLD, jLabel190.getFont().getSize()+6));
        jLabel190.setForeground(new java.awt.Color(0, 255, 102));
        jLabel190.setText(bundle.getString("CrInstrument.jLabel190.text")); 
        jPanel19.add(jLabel190);
        jLabel190.setBounds(20, 60, 320, 30);

        jLabel191.setFont(jLabel191.getFont().deriveFont(jLabel191.getFont().getStyle() | java.awt.Font.BOLD, jLabel191.getFont().getSize()+6));
        jLabel191.setForeground(new java.awt.Color(0, 255, 102));
        jLabel191.setText(bundle.getString("CrInstrument.jLabel191.text")); 
        jPanel19.add(jLabel191);
        jLabel191.setBounds(20, 60, 320, 30);

        jLabel192.setFont(jLabel192.getFont().deriveFont(jLabel192.getFont().getStyle() | java.awt.Font.BOLD, jLabel192.getFont().getSize()+6));
        jLabel192.setForeground(new java.awt.Color(0, 255, 102));
        jLabel192.setText(bundle.getString("CrInstrument.jLabel192.text")); 
        jPanel19.add(jLabel192);
        jLabel192.setBounds(20, 60, 320, 30);

        jLabel193.setFont(jLabel193.getFont().deriveFont(jLabel193.getFont().getStyle() | java.awt.Font.BOLD, jLabel193.getFont().getSize()+6));
        jLabel193.setForeground(new java.awt.Color(0, 255, 102));
        jLabel193.setText(bundle.getString("CrInstrument.jLabel193.text")); 
        jPanel19.add(jLabel193);
        jLabel193.setBounds(20, 60, 320, 30);

        jLabel194.setFont(jLabel194.getFont().deriveFont(jLabel194.getFont().getStyle() | java.awt.Font.BOLD, jLabel194.getFont().getSize()+6));
        jLabel194.setForeground(new java.awt.Color(0, 255, 102));
        jLabel194.setText(bundle.getString("CrInstrument.jLabel194.text")); 
        jPanel19.add(jLabel194);
        jLabel194.setBounds(20, 60, 320, 30);

        jLabel195.setFont(jLabel195.getFont().deriveFont(jLabel195.getFont().getStyle() | java.awt.Font.BOLD, jLabel195.getFont().getSize()+6));
        jLabel195.setForeground(new java.awt.Color(0, 255, 102));
        jLabel195.setText(bundle.getString("CrInstrument.jLabel195.text")); 
        jPanel19.add(jLabel195);
        jLabel195.setBounds(20, 60, 320, 30);

        jLabel196.setFont(jLabel196.getFont().deriveFont(jLabel196.getFont().getStyle() | java.awt.Font.BOLD, jLabel196.getFont().getSize()+6));
        jLabel196.setForeground(new java.awt.Color(0, 255, 102));
        jLabel196.setText(bundle.getString("CrInstrument.jLabel196.text")); 
        jPanel19.add(jLabel196);
        jLabel196.setBounds(20, 60, 320, 30);

        jLabel197.setFont(jLabel197.getFont().deriveFont(jLabel197.getFont().getStyle() | java.awt.Font.BOLD, jLabel197.getFont().getSize()+6));
        jLabel197.setForeground(new java.awt.Color(0, 255, 102));
        jLabel197.setText(bundle.getString("CrInstrument.jLabel197.text")); 
        jPanel19.add(jLabel197);
        jLabel197.setBounds(20, 60, 320, 30);

        jLabel198.setFont(jLabel198.getFont().deriveFont(jLabel198.getFont().getStyle() | java.awt.Font.BOLD, jLabel198.getFont().getSize()+6));
        jLabel198.setForeground(new java.awt.Color(0, 255, 102));
        jLabel198.setText(bundle.getString("CrInstrument.jLabel198.text")); 
        jPanel19.add(jLabel198);
        jLabel198.setBounds(20, 60, 320, 30);

        jLabel199.setFont(jLabel199.getFont().deriveFont(jLabel199.getFont().getStyle() | java.awt.Font.BOLD, jLabel199.getFont().getSize()+6));
        jLabel199.setForeground(new java.awt.Color(0, 255, 102));
        jLabel199.setText(bundle.getString("CrInstrument.jLabel199.text")); 
        jPanel19.add(jLabel199);
        jLabel199.setBounds(20, 60, 320, 30);

        jLabel200.setFont(jLabel200.getFont().deriveFont(jLabel200.getFont().getStyle() | java.awt.Font.BOLD, jLabel200.getFont().getSize()+6));
        jLabel200.setForeground(new java.awt.Color(0, 255, 102));
        jLabel200.setText(bundle.getString("CrInstrument.jLabel200.text")); 
        jPanel19.add(jLabel200);
        jLabel200.setBounds(20, 60, 320, 30);

        jLabel201.setFont(jLabel201.getFont().deriveFont(jLabel201.getFont().getStyle() | java.awt.Font.BOLD, jLabel201.getFont().getSize()+6));
        jLabel201.setForeground(new java.awt.Color(0, 255, 102));
        jLabel201.setText(bundle.getString("CrInstrument.jLabel201.text")); 
        jPanel19.add(jLabel201);
        jLabel201.setBounds(20, 60, 320, 30);

        jLabel202.setFont(jLabel202.getFont().deriveFont(jLabel202.getFont().getStyle() | java.awt.Font.BOLD, jLabel202.getFont().getSize()+6));
        jLabel202.setForeground(new java.awt.Color(0, 255, 102));
        jLabel202.setText(bundle.getString("CrInstrument.jLabel202.text")); 
        jPanel19.add(jLabel202);
        jLabel202.setBounds(20, 60, 320, 30);

        jLabel203.setFont(jLabel203.getFont().deriveFont(jLabel203.getFont().getStyle() | java.awt.Font.BOLD, jLabel203.getFont().getSize()+6));
        jLabel203.setForeground(new java.awt.Color(0, 255, 102));
        jLabel203.setText(bundle.getString("CrInstrument.jLabel203.text")); 
        jPanel19.add(jLabel203);
        jLabel203.setBounds(20, 60, 320, 30);

        jLabel204.setFont(jLabel204.getFont().deriveFont(jLabel204.getFont().getStyle() | java.awt.Font.BOLD, jLabel204.getFont().getSize()+6));
        jLabel204.setForeground(new java.awt.Color(0, 255, 102));
        jLabel204.setText(bundle.getString("CrInstrument.jLabel204.text")); 
        jPanel19.add(jLabel204);
        jLabel204.setBounds(20, 60, 320, 30);

        jLabel205.setFont(jLabel205.getFont().deriveFont(jLabel205.getFont().getStyle() | java.awt.Font.BOLD, jLabel205.getFont().getSize()+6));
        jLabel205.setForeground(new java.awt.Color(0, 255, 102));
        jLabel205.setText(bundle.getString("CrInstrument.jLabel205.text")); 
        jPanel19.add(jLabel205);
        jLabel205.setBounds(20, 60, 320, 30);

        jLabel206.setFont(jLabel206.getFont().deriveFont(jLabel206.getFont().getStyle() | java.awt.Font.BOLD, jLabel206.getFont().getSize()+6));
        jLabel206.setForeground(new java.awt.Color(0, 255, 102));
        jLabel206.setText(bundle.getString("CrInstrument.jLabel206.text")); 
        jPanel19.add(jLabel206);
        jLabel206.setBounds(20, 60, 320, 30);

        jLabel207.setFont(jLabel207.getFont().deriveFont(jLabel207.getFont().getStyle() | java.awt.Font.BOLD, jLabel207.getFont().getSize()+6));
        jLabel207.setForeground(new java.awt.Color(0, 255, 102));
        jLabel207.setText(bundle.getString("CrInstrument.jLabel207.text")); 
        jPanel19.add(jLabel207);
        jLabel207.setBounds(20, 60, 320, 30);

        jLabel208.setFont(jLabel208.getFont().deriveFont(jLabel208.getFont().getStyle() | java.awt.Font.BOLD, jLabel208.getFont().getSize()+6));
        jLabel208.setForeground(new java.awt.Color(0, 255, 102));
        jLabel208.setText(bundle.getString("CrInstrument.jLabel208.text")); 
        jPanel19.add(jLabel208);
        jLabel208.setBounds(20, 60, 320, 30);

        jLabel209.setFont(jLabel209.getFont().deriveFont(jLabel209.getFont().getStyle() | java.awt.Font.BOLD, jLabel209.getFont().getSize()+6));
        jLabel209.setForeground(new java.awt.Color(0, 255, 102));
        jLabel209.setText(bundle.getString("CrInstrument.jLabel209.text")); 
        jPanel19.add(jLabel209);
        jLabel209.setBounds(20, 60, 320, 30);

        jLabel210.setFont(jLabel210.getFont().deriveFont(jLabel210.getFont().getStyle() | java.awt.Font.BOLD, jLabel210.getFont().getSize()+6));
        jLabel210.setForeground(new java.awt.Color(0, 255, 102));
        jLabel210.setText(bundle.getString("CrInstrument.jLabel210.text")); 
        jPanel19.add(jLabel210);
        jLabel210.setBounds(20, 60, 320, 30);

        jLabel211.setFont(jLabel211.getFont().deriveFont(jLabel211.getFont().getStyle() | java.awt.Font.BOLD, jLabel211.getFont().getSize()+6));
        jLabel211.setForeground(new java.awt.Color(0, 255, 102));
        jLabel211.setText(bundle.getString("CrInstrument.jLabel211.text")); 
        jPanel19.add(jLabel211);
        jLabel211.setBounds(20, 60, 320, 30);

        jLabel212.setFont(jLabel212.getFont().deriveFont(jLabel212.getFont().getStyle() | java.awt.Font.BOLD, jLabel212.getFont().getSize()+6));
        jLabel212.setForeground(new java.awt.Color(0, 255, 102));
        jLabel212.setText(bundle.getString("CrInstrument.jLabel212.text")); 
        jPanel19.add(jLabel212);
        jLabel212.setBounds(20, 60, 320, 30);

        jLabel213.setFont(jLabel213.getFont().deriveFont(jLabel213.getFont().getStyle() | java.awt.Font.BOLD, jLabel213.getFont().getSize()+6));
        jLabel213.setForeground(new java.awt.Color(0, 255, 102));
        jLabel213.setText(bundle.getString("CrInstrument.jLabel213.text")); 
        jPanel19.add(jLabel213);
        jLabel213.setBounds(20, 60, 320, 30);

        jLabel214.setFont(jLabel214.getFont().deriveFont(jLabel214.getFont().getStyle() | java.awt.Font.BOLD, jLabel214.getFont().getSize()+6));
        jLabel214.setForeground(new java.awt.Color(0, 255, 102));
        jLabel214.setText(bundle.getString("CrInstrument.jLabel214.text")); 
        jPanel19.add(jLabel214);
        jLabel214.setBounds(20, 60, 320, 30);

        jLabel215.setFont(jLabel215.getFont().deriveFont(jLabel215.getFont().getStyle() | java.awt.Font.BOLD, jLabel215.getFont().getSize()+6));
        jLabel215.setForeground(new java.awt.Color(0, 255, 102));
        jLabel215.setText(bundle.getString("CrInstrument.jLabel215.text")); 
        jPanel19.add(jLabel215);
        jLabel215.setBounds(20, 60, 320, 30);

        jLabel216.setFont(jLabel216.getFont().deriveFont(jLabel216.getFont().getStyle() | java.awt.Font.BOLD, jLabel216.getFont().getSize()+6));
        jLabel216.setForeground(new java.awt.Color(0, 255, 102));
        jLabel216.setText(bundle.getString("CrInstrument.jLabel216.text")); 
        jPanel19.add(jLabel216);
        jLabel216.setBounds(20, 60, 320, 30);

        jLabel217.setFont(jLabel217.getFont().deriveFont(jLabel217.getFont().getStyle() | java.awt.Font.BOLD, jLabel217.getFont().getSize()+6));
        jLabel217.setForeground(new java.awt.Color(255, 255, 51));
        jLabel217.setText(bundle.getString("CrInstrument.jLabel217.text")); 
        jPanel19.add(jLabel217);
        jLabel217.setBounds(20, 110, 320, 30);

        jLabel218.setFont(jLabel218.getFont().deriveFont(jLabel218.getFont().getStyle() | java.awt.Font.BOLD, jLabel218.getFont().getSize()+6));
        jLabel218.setForeground(new java.awt.Color(255, 255, 51));
        jLabel218.setText(bundle.getString("CrInstrument.jLabel218.text")); 
        jPanel19.add(jLabel218);
        jLabel218.setBounds(20, 110, 320, 30);

        jLabel219.setFont(jLabel219.getFont().deriveFont(jLabel219.getFont().getStyle() | java.awt.Font.BOLD, jLabel219.getFont().getSize()+6));
        jLabel219.setForeground(new java.awt.Color(255, 255, 51));
        jLabel219.setText(bundle.getString("CrInstrument.jLabel219.text")); 
        jPanel19.add(jLabel219);
        jLabel219.setBounds(20, 110, 320, 30);

        jLabel220.setFont(jLabel220.getFont().deriveFont(jLabel220.getFont().getStyle() | java.awt.Font.BOLD, jLabel220.getFont().getSize()+6));
        jLabel220.setForeground(new java.awt.Color(255, 255, 51));
        jLabel220.setText(bundle.getString("CrInstrument.jLabel220.text")); 
        jPanel19.add(jLabel220);
        jLabel220.setBounds(20, 110, 320, 30);

        jLabel221.setFont(jLabel221.getFont().deriveFont(jLabel221.getFont().getStyle() | java.awt.Font.BOLD, jLabel221.getFont().getSize()+6));
        jLabel221.setForeground(new java.awt.Color(255, 255, 51));
        jLabel221.setText(bundle.getString("CrInstrument.jLabel221.text")); 
        jPanel19.add(jLabel221);
        jLabel221.setBounds(20, 110, 320, 30);

        jLabel222.setFont(jLabel222.getFont().deriveFont(jLabel222.getFont().getStyle() | java.awt.Font.BOLD, jLabel222.getFont().getSize()+6));
        jLabel222.setForeground(new java.awt.Color(255, 255, 51));
        jLabel222.setText(bundle.getString("CrInstrument.jLabel222.text")); 
        jPanel19.add(jLabel222);
        jLabel222.setBounds(20, 110, 320, 30);

        jLabel223.setFont(jLabel223.getFont().deriveFont(jLabel223.getFont().getStyle() | java.awt.Font.BOLD, jLabel223.getFont().getSize()+6));
        jLabel223.setForeground(new java.awt.Color(255, 255, 51));
        jLabel223.setText(bundle.getString("CrInstrument.jLabel223.text")); 
        jPanel19.add(jLabel223);
        jLabel223.setBounds(20, 110, 320, 30);

        jLabel224.setFont(jLabel224.getFont().deriveFont(jLabel224.getFont().getStyle() | java.awt.Font.BOLD, jLabel224.getFont().getSize()+6));
        jLabel224.setForeground(new java.awt.Color(255, 255, 51));
        jLabel224.setText(bundle.getString("CrInstrument.jLabel224.text")); 
        jPanel19.add(jLabel224);
        jLabel224.setBounds(20, 110, 320, 30);

        jLabel225.setFont(jLabel225.getFont().deriveFont(jLabel225.getFont().getStyle() | java.awt.Font.BOLD, jLabel225.getFont().getSize()+6));
        jLabel225.setForeground(new java.awt.Color(255, 255, 51));
        jLabel225.setText(bundle.getString("CrInstrument.jLabel225.text")); 
        jPanel19.add(jLabel225);
        jLabel225.setBounds(20, 110, 320, 30);

        jLabel226.setFont(jLabel226.getFont().deriveFont(jLabel226.getFont().getStyle() | java.awt.Font.BOLD, jLabel226.getFont().getSize()+6));
        jLabel226.setForeground(new java.awt.Color(255, 255, 51));
        jLabel226.setText(bundle.getString("CrInstrument.jLabel226.text")); 
        jPanel19.add(jLabel226);
        jLabel226.setBounds(20, 110, 320, 30);

        jLabel227.setFont(jLabel227.getFont().deriveFont(jLabel227.getFont().getStyle() | java.awt.Font.BOLD, jLabel227.getFont().getSize()+6));
        jLabel227.setForeground(new java.awt.Color(255, 255, 51));
        jLabel227.setText(bundle.getString("CrInstrument.jLabel227.text")); 
        jPanel19.add(jLabel227);
        jLabel227.setBounds(20, 110, 320, 30);

        jLabel228.setFont(jLabel228.getFont().deriveFont(jLabel228.getFont().getStyle() | java.awt.Font.BOLD, jLabel228.getFont().getSize()+6));
        jLabel228.setForeground(new java.awt.Color(255, 255, 51));
        jLabel228.setText(bundle.getString("CrInstrument.jLabel228.text")); 
        jPanel19.add(jLabel228);
        jLabel228.setBounds(20, 110, 320, 30);

        jLabel229.setFont(jLabel229.getFont().deriveFont(jLabel229.getFont().getStyle() | java.awt.Font.BOLD, jLabel229.getFont().getSize()+6));
        jLabel229.setForeground(new java.awt.Color(255, 255, 51));
        jLabel229.setText(bundle.getString("CrInstrument.jLabel229.text")); 
        jPanel19.add(jLabel229);
        jLabel229.setBounds(20, 110, 320, 30);

        jLabel230.setFont(jLabel230.getFont().deriveFont(jLabel230.getFont().getStyle() | java.awt.Font.BOLD, jLabel230.getFont().getSize()+6));
        jLabel230.setForeground(new java.awt.Color(255, 255, 51));
        jLabel230.setText(bundle.getString("CrInstrument.jLabel230.text")); 
        jPanel19.add(jLabel230);
        jLabel230.setBounds(20, 110, 320, 30);

        jLabel231.setFont(jLabel231.getFont().deriveFont(jLabel231.getFont().getStyle() | java.awt.Font.BOLD, jLabel231.getFont().getSize()+6));
        jLabel231.setForeground(new java.awt.Color(255, 255, 51));
        jLabel231.setText(bundle.getString("CrInstrument.jLabel231.text")); 
        jPanel19.add(jLabel231);
        jLabel231.setBounds(20, 110, 320, 30);

        jLabel232.setFont(jLabel232.getFont().deriveFont(jLabel232.getFont().getStyle() | java.awt.Font.BOLD, jLabel232.getFont().getSize()+6));
        jLabel232.setForeground(new java.awt.Color(255, 255, 51));
        jLabel232.setText(bundle.getString("CrInstrument.jLabel232.text")); 
        jPanel19.add(jLabel232);
        jLabel232.setBounds(20, 110, 320, 30);

        jLabel233.setFont(jLabel233.getFont().deriveFont(jLabel233.getFont().getStyle() | java.awt.Font.BOLD, jLabel233.getFont().getSize()+6));
        jLabel233.setForeground(new java.awt.Color(255, 255, 51));
        jLabel233.setText(bundle.getString("CrInstrument.jLabel233.text")); 
        jPanel19.add(jLabel233);
        jLabel233.setBounds(20, 110, 320, 30);

        jLabel234.setFont(jLabel234.getFont().deriveFont(jLabel234.getFont().getStyle() | java.awt.Font.BOLD, jLabel234.getFont().getSize()+6));
        jLabel234.setForeground(new java.awt.Color(255, 255, 51));
        jLabel234.setText(bundle.getString("CrInstrument.jLabel234.text")); 
        jPanel19.add(jLabel234);
        jLabel234.setBounds(20, 110, 320, 30);

        jLabel235.setFont(jLabel235.getFont().deriveFont(jLabel235.getFont().getStyle() | java.awt.Font.BOLD, jLabel235.getFont().getSize()+6));
        jLabel235.setForeground(new java.awt.Color(255, 255, 51));
        jLabel235.setText(bundle.getString("CrInstrument.jLabel235.text")); 
        jPanel19.add(jLabel235);
        jLabel235.setBounds(20, 110, 320, 30);

        jLabel236.setFont(jLabel236.getFont().deriveFont(jLabel236.getFont().getStyle() | java.awt.Font.BOLD, jLabel236.getFont().getSize()+6));
        jLabel236.setForeground(new java.awt.Color(255, 255, 51));
        jLabel236.setText(bundle.getString("CrInstrument.jLabel236.text")); 
        jPanel19.add(jLabel236);
        jLabel236.setBounds(20, 110, 320, 30);

        jLabel237.setFont(jLabel237.getFont().deriveFont(jLabel237.getFont().getStyle() | java.awt.Font.BOLD, jLabel237.getFont().getSize()+6));
        jLabel237.setForeground(new java.awt.Color(255, 255, 51));
        jLabel237.setText(bundle.getString("CrInstrument.jLabel237.text")); 
        jPanel19.add(jLabel237);
        jLabel237.setBounds(20, 110, 320, 30);

        jLabel238.setFont(jLabel238.getFont().deriveFont(jLabel238.getFont().getStyle() | java.awt.Font.BOLD, jLabel238.getFont().getSize()+6));
        jLabel238.setForeground(new java.awt.Color(255, 255, 51));
        jLabel238.setText(bundle.getString("CrInstrument.jLabel238.text")); 
        jPanel19.add(jLabel238);
        jLabel238.setBounds(20, 110, 320, 30);

        jLabel239.setFont(jLabel239.getFont().deriveFont(jLabel239.getFont().getStyle() | java.awt.Font.BOLD, jLabel239.getFont().getSize()+6));
        jLabel239.setForeground(new java.awt.Color(255, 255, 51));
        jLabel239.setText(bundle.getString("CrInstrument.jLabel239.text")); 
        jPanel19.add(jLabel239);
        jLabel239.setBounds(20, 110, 320, 30);

        jLabel240.setFont(jLabel240.getFont().deriveFont(jLabel240.getFont().getStyle() | java.awt.Font.BOLD, jLabel240.getFont().getSize()+6));
        jLabel240.setForeground(new java.awt.Color(255, 255, 51));
        jLabel240.setText(bundle.getString("CrInstrument.jLabel240.text")); 
        jPanel19.add(jLabel240);
        jLabel240.setBounds(20, 110, 320, 30);

        jLabel241.setFont(jLabel241.getFont().deriveFont(jLabel241.getFont().getStyle() | java.awt.Font.BOLD, jLabel241.getFont().getSize()+6));
        jLabel241.setForeground(new java.awt.Color(255, 255, 51));
        jLabel241.setText(bundle.getString("CrInstrument.jLabel241.text")); 
        jPanel19.add(jLabel241);
        jLabel241.setBounds(20, 110, 320, 30);

        jLabel242.setFont(jLabel242.getFont().deriveFont(jLabel242.getFont().getStyle() | java.awt.Font.BOLD, jLabel242.getFont().getSize()+6));
        jLabel242.setForeground(new java.awt.Color(255, 255, 51));
        jLabel242.setText(bundle.getString("CrInstrument.jLabel242.text")); 
        jPanel19.add(jLabel242);
        jLabel242.setBounds(20, 110, 320, 30);

        jLabel243.setFont(jLabel243.getFont().deriveFont(jLabel243.getFont().getStyle() | java.awt.Font.BOLD, jLabel243.getFont().getSize()+6));
        jLabel243.setForeground(new java.awt.Color(255, 255, 51));
        jLabel243.setText(bundle.getString("CrInstrument.jLabel243.text")); 
        jPanel19.add(jLabel243);
        jLabel243.setBounds(20, 110, 320, 30);

        jLabel244.setFont(jLabel244.getFont().deriveFont(jLabel244.getFont().getStyle() | java.awt.Font.BOLD, jLabel244.getFont().getSize()+6));
        jLabel244.setForeground(new java.awt.Color(255, 255, 51));
        jLabel244.setText(bundle.getString("CrInstrument.jLabel244.text")); 
        jPanel19.add(jLabel244);
        jLabel244.setBounds(20, 110, 320, 30);

        jLabel245.setFont(jLabel245.getFont().deriveFont(jLabel245.getFont().getStyle() | java.awt.Font.BOLD, jLabel245.getFont().getSize()+6));
        jLabel245.setForeground(new java.awt.Color(255, 255, 51));
        jLabel245.setText(bundle.getString("CrInstrument.jLabel245.text")); 
        jPanel19.add(jLabel245);
        jLabel245.setBounds(20, 110, 320, 30);

        jLabel246.setFont(jLabel246.getFont().deriveFont(jLabel246.getFont().getStyle() | java.awt.Font.BOLD, jLabel246.getFont().getSize()+6));
        jLabel246.setForeground(new java.awt.Color(255, 255, 51));
        jLabel246.setText(bundle.getString("CrInstrument.jLabel246.text")); 
        jPanel19.add(jLabel246);
        jLabel246.setBounds(20, 110, 320, 30);

        jLabel247.setFont(jLabel247.getFont().deriveFont(jLabel247.getFont().getStyle() | java.awt.Font.BOLD, jLabel247.getFont().getSize()+6));
        jLabel247.setForeground(new java.awt.Color(255, 255, 51));
        jLabel247.setText(bundle.getString("CrInstrument.jLabel247.text")); 
        jPanel19.add(jLabel247);
        jLabel247.setBounds(20, 110, 320, 30);

        jLabel248.setFont(jLabel248.getFont().deriveFont(jLabel248.getFont().getStyle() | java.awt.Font.BOLD, jLabel248.getFont().getSize()+6));
        jLabel248.setForeground(new java.awt.Color(255, 255, 51));
        jLabel248.setText(bundle.getString("CrInstrument.jLabel248.text")); 
        jPanel19.add(jLabel248);
        jLabel248.setBounds(20, 110, 320, 30);

        jLabel249.setFont(jLabel249.getFont().deriveFont(jLabel249.getFont().getStyle() | java.awt.Font.BOLD, jLabel249.getFont().getSize()+6));
        jLabel249.setForeground(new java.awt.Color(255, 255, 51));
        jLabel249.setText(bundle.getString("CrInstrument.jLabel249.text")); 
        jPanel19.add(jLabel249);
        jLabel249.setBounds(20, 110, 320, 30);

        jLabel250.setFont(jLabel250.getFont().deriveFont(jLabel250.getFont().getStyle() | java.awt.Font.BOLD, jLabel250.getFont().getSize()+6));
        jLabel250.setForeground(new java.awt.Color(255, 255, 51));
        jLabel250.setText(bundle.getString("CrInstrument.jLabel250.text")); 
        jPanel19.add(jLabel250);
        jLabel250.setBounds(20, 110, 320, 30);

        jLabel251.setFont(jLabel251.getFont().deriveFont(jLabel251.getFont().getStyle() | java.awt.Font.BOLD, jLabel251.getFont().getSize()+6));
        jLabel251.setForeground(new java.awt.Color(255, 255, 51));
        jLabel251.setText(bundle.getString("CrInstrument.jLabel251.text")); 
        jPanel19.add(jLabel251);
        jLabel251.setBounds(20, 110, 320, 30);

        jLabel252.setFont(jLabel252.getFont().deriveFont(jLabel252.getFont().getStyle() | java.awt.Font.BOLD, jLabel252.getFont().getSize()+6));
        jLabel252.setForeground(new java.awt.Color(255, 255, 51));
        jLabel252.setText(bundle.getString("CrInstrument.jLabel252.text")); 
        jPanel19.add(jLabel252);
        jLabel252.setBounds(20, 110, 320, 30);

        jLabel253.setFont(jLabel253.getFont().deriveFont(jLabel253.getFont().getStyle() | java.awt.Font.BOLD, jLabel253.getFont().getSize()+6));
        jLabel253.setForeground(new java.awt.Color(255, 255, 51));
        jLabel253.setText(bundle.getString("CrInstrument.jLabel253.text")); 
        jPanel19.add(jLabel253);
        jLabel253.setBounds(20, 110, 320, 30);

        jLabel254.setFont(jLabel254.getFont().deriveFont(jLabel254.getFont().getStyle() | java.awt.Font.BOLD, jLabel254.getFont().getSize()+6));
        jLabel254.setForeground(new java.awt.Color(255, 255, 51));
        jLabel254.setText(bundle.getString("CrInstrument.jLabel254.text")); 
        jPanel19.add(jLabel254);
        jLabel254.setBounds(20, 110, 320, 30);

        jLabel255.setFont(jLabel255.getFont().deriveFont(jLabel255.getFont().getStyle() | java.awt.Font.BOLD, jLabel255.getFont().getSize()+6));
        jLabel255.setForeground(new java.awt.Color(255, 255, 51));
        jLabel255.setText(bundle.getString("CrInstrument.jLabel255.text")); 
        jPanel19.add(jLabel255);
        jLabel255.setBounds(20, 110, 320, 30);

        jLabel256.setFont(jLabel256.getFont().deriveFont(jLabel256.getFont().getStyle() | java.awt.Font.BOLD, jLabel256.getFont().getSize()+6));
        jLabel256.setForeground(new java.awt.Color(255, 255, 51));
        jLabel256.setText(bundle.getString("CrInstrument.jLabel256.text")); 
        jPanel19.add(jLabel256);
        jLabel256.setBounds(20, 110, 320, 30);

        jLabel257.setFont(jLabel257.getFont().deriveFont(jLabel257.getFont().getStyle() | java.awt.Font.BOLD, jLabel257.getFont().getSize()+6));
        jLabel257.setForeground(new java.awt.Color(255, 255, 51));
        jLabel257.setText(bundle.getString("CrInstrument.jLabel257.text")); 
        jPanel19.add(jLabel257);
        jLabel257.setBounds(20, 110, 320, 30);

        jLabel258.setFont(jLabel258.getFont().deriveFont(jLabel258.getFont().getStyle() | java.awt.Font.BOLD, jLabel258.getFont().getSize()+6));
        jLabel258.setForeground(new java.awt.Color(255, 255, 51));
        jLabel258.setText(bundle.getString("CrInstrument.jLabel258.text")); 
        jPanel19.add(jLabel258);
        jLabel258.setBounds(20, 110, 320, 30);

        jLabel259.setFont(jLabel259.getFont().deriveFont(jLabel259.getFont().getStyle() | java.awt.Font.BOLD, jLabel259.getFont().getSize()+6));
        jLabel259.setForeground(new java.awt.Color(255, 255, 51));
        jLabel259.setText(bundle.getString("CrInstrument.jLabel259.text")); 
        jPanel19.add(jLabel259);
        jLabel259.setBounds(20, 110, 320, 30);

        jLabel260.setFont(jLabel260.getFont().deriveFont(jLabel260.getFont().getStyle() | java.awt.Font.BOLD, jLabel260.getFont().getSize()+6));
        jLabel260.setForeground(new java.awt.Color(255, 255, 51));
        jLabel260.setText(bundle.getString("CrInstrument.jLabel260.text")); 
        jPanel19.add(jLabel260);
        jLabel260.setBounds(20, 110, 320, 30);

        jLabel261.setFont(jLabel261.getFont().deriveFont(jLabel261.getFont().getStyle() | java.awt.Font.BOLD, jLabel261.getFont().getSize()+6));
        jLabel261.setForeground(new java.awt.Color(255, 255, 51));
        jLabel261.setText(bundle.getString("CrInstrument.jLabel261.text")); 
        jPanel19.add(jLabel261);
        jLabel261.setBounds(20, 110, 320, 30);

        jLabel262.setFont(jLabel262.getFont().deriveFont(jLabel262.getFont().getStyle() | java.awt.Font.BOLD, jLabel262.getFont().getSize()+6));
        jLabel262.setForeground(new java.awt.Color(255, 255, 51));
        jLabel262.setText(bundle.getString("CrInstrument.jLabel262.text")); 
        jPanel19.add(jLabel262);
        jLabel262.setBounds(20, 110, 320, 30);

        jLabel263.setFont(jLabel263.getFont().deriveFont(jLabel263.getFont().getStyle() | java.awt.Font.BOLD, jLabel263.getFont().getSize()+6));
        jLabel263.setForeground(new java.awt.Color(255, 255, 51));
        jLabel263.setText(bundle.getString("CrInstrument.jLabel263.text")); 
        jPanel19.add(jLabel263);
        jLabel263.setBounds(20, 110, 320, 30);

        jLabel264.setFont(jLabel264.getFont().deriveFont(jLabel264.getFont().getStyle() | java.awt.Font.BOLD, jLabel264.getFont().getSize()+36));
        jLabel264.setForeground(new java.awt.Color(255, 0, 51));
        jLabel264.setText(bundle.getString("CrInstrument.jLabel264.text")); 
        jPanel19.add(jLabel264);
        jLabel264.setBounds(60, 150, 300, 60);

        jLabel265.setFont(jLabel265.getFont().deriveFont(jLabel265.getFont().getStyle() | java.awt.Font.BOLD, jLabel265.getFont().getSize()+36));
        jLabel265.setForeground(new java.awt.Color(255, 0, 51));
        jLabel265.setText(bundle.getString("CrInstrument.jLabel265.text")); 
        jPanel19.add(jLabel265);
        jLabel265.setBounds(60, 150, 300, 60);

        jLabel266.setFont(jLabel266.getFont().deriveFont(jLabel266.getFont().getStyle() | java.awt.Font.BOLD, jLabel266.getFont().getSize()+36));
        jLabel266.setForeground(new java.awt.Color(255, 0, 51));
        jLabel266.setText(bundle.getString("CrInstrument.jLabel266.text")); 
        jPanel19.add(jLabel266);
        jLabel266.setBounds(60, 150, 300, 60);

        jLabel267.setFont(jLabel267.getFont().deriveFont(jLabel267.getFont().getStyle() | java.awt.Font.BOLD, jLabel267.getFont().getSize()+36));
        jLabel267.setForeground(new java.awt.Color(255, 0, 51));
        jLabel267.setText(bundle.getString("CrInstrument.jLabel267.text")); 
        jPanel19.add(jLabel267);
        jLabel267.setBounds(60, 150, 300, 60);

        jLabel268.setFont(jLabel268.getFont().deriveFont(jLabel268.getFont().getStyle() | java.awt.Font.BOLD, jLabel268.getFont().getSize()+36));
        jLabel268.setForeground(new java.awt.Color(255, 0, 51));
        jLabel268.setText(bundle.getString("CrInstrument.jLabel268.text")); 
        jPanel19.add(jLabel268);
        jLabel268.setBounds(60, 150, 300, 60);

        jLabel269.setFont(jLabel269.getFont().deriveFont(jLabel269.getFont().getStyle() | java.awt.Font.BOLD, jLabel269.getFont().getSize()+36));
        jLabel269.setForeground(new java.awt.Color(255, 0, 51));
        jLabel269.setText(bundle.getString("CrInstrument.jLabel269.text")); 
        jPanel19.add(jLabel269);
        jLabel269.setBounds(60, 150, 300, 60);

        jLabel270.setFont(jLabel270.getFont().deriveFont(jLabel270.getFont().getStyle() | java.awt.Font.BOLD, jLabel270.getFont().getSize()+36));
        jLabel270.setForeground(new java.awt.Color(255, 0, 51));
        jLabel270.setText(bundle.getString("CrInstrument.jLabel270.text")); 
        jPanel19.add(jLabel270);
        jLabel270.setBounds(60, 150, 300, 60);

        jLabel271.setFont(jLabel271.getFont().deriveFont(jLabel271.getFont().getStyle() | java.awt.Font.BOLD, jLabel271.getFont().getSize()+36));
        jLabel271.setForeground(new java.awt.Color(255, 0, 51));
        jLabel271.setText(bundle.getString("CrInstrument.jLabel271.text")); 
        jPanel19.add(jLabel271);
        jLabel271.setBounds(60, 150, 300, 60);

        jLabel272.setFont(jLabel272.getFont().deriveFont(jLabel272.getFont().getStyle() | java.awt.Font.BOLD, jLabel272.getFont().getSize()+36));
        jLabel272.setForeground(new java.awt.Color(255, 0, 51));
        jLabel272.setText(bundle.getString("CrInstrument.jLabel272.text")); 
        jPanel19.add(jLabel272);
        jLabel272.setBounds(60, 150, 300, 60);

        jLabel273.setFont(jLabel273.getFont().deriveFont(jLabel273.getFont().getStyle() | java.awt.Font.BOLD, jLabel273.getFont().getSize()+36));
        jLabel273.setForeground(new java.awt.Color(255, 0, 51));
        jLabel273.setText(bundle.getString("CrInstrument.jLabel273.text")); 
        jPanel19.add(jLabel273);
        jLabel273.setBounds(60, 150, 300, 60);

        jLabel274.setFont(jLabel274.getFont().deriveFont(jLabel274.getFont().getStyle() | java.awt.Font.BOLD, jLabel274.getFont().getSize()+36));
        jLabel274.setForeground(new java.awt.Color(255, 0, 51));
        jLabel274.setText(bundle.getString("CrInstrument.jLabel274.text")); 
        jPanel19.add(jLabel274);
        jLabel274.setBounds(60, 150, 300, 60);

        jLabel275.setFont(jLabel275.getFont().deriveFont(jLabel275.getFont().getStyle() | java.awt.Font.BOLD, jLabel275.getFont().getSize()+36));
        jLabel275.setForeground(new java.awt.Color(255, 0, 51));
        jLabel275.setText(bundle.getString("CrInstrument.jLabel275.text")); 
        jPanel19.add(jLabel275);
        jLabel275.setBounds(60, 150, 300, 60);

        jLabel276.setFont(jLabel276.getFont().deriveFont(jLabel276.getFont().getStyle() | java.awt.Font.BOLD, jLabel276.getFont().getSize()+36));
        jLabel276.setForeground(new java.awt.Color(255, 0, 51));
        jLabel276.setText(bundle.getString("CrInstrument.jLabel276.text")); 
        jPanel19.add(jLabel276);
        jLabel276.setBounds(60, 150, 300, 60);

        jLabel277.setFont(jLabel277.getFont().deriveFont(jLabel277.getFont().getStyle() | java.awt.Font.BOLD, jLabel277.getFont().getSize()+36));
        jLabel277.setForeground(new java.awt.Color(255, 0, 51));
        jLabel277.setText(bundle.getString("CrInstrument.jLabel277.text")); 
        jPanel19.add(jLabel277);
        jLabel277.setBounds(60, 150, 300, 60);

        jLabel278.setFont(jLabel278.getFont().deriveFont(jLabel278.getFont().getStyle() | java.awt.Font.BOLD, jLabel278.getFont().getSize()+36));
        jLabel278.setForeground(new java.awt.Color(255, 0, 51));
        jLabel278.setText(bundle.getString("CrInstrument.jLabel278.text")); 
        jPanel19.add(jLabel278);
        jLabel278.setBounds(60, 150, 300, 60);

        jLabel279.setFont(jLabel279.getFont().deriveFont(jLabel279.getFont().getStyle() | java.awt.Font.BOLD, jLabel279.getFont().getSize()+36));
        jLabel279.setForeground(new java.awt.Color(255, 0, 51));
        jLabel279.setText(bundle.getString("CrInstrument.jLabel279.text")); 
        jPanel19.add(jLabel279);
        jLabel279.setBounds(60, 150, 300, 60);

        jLabel280.setFont(jLabel280.getFont().deriveFont(jLabel280.getFont().getStyle() | java.awt.Font.BOLD, jLabel280.getFont().getSize()+36));
        jLabel280.setForeground(new java.awt.Color(255, 0, 51));
        jLabel280.setText(bundle.getString("CrInstrument.jLabel280.text")); 
        jPanel19.add(jLabel280);
        jLabel280.setBounds(60, 150, 300, 60);

        jLabel281.setFont(jLabel281.getFont().deriveFont(jLabel281.getFont().getStyle() | java.awt.Font.BOLD, jLabel281.getFont().getSize()+36));
        jLabel281.setForeground(new java.awt.Color(255, 0, 51));
        jLabel281.setText(bundle.getString("CrInstrument.jLabel281.text")); 
        jPanel19.add(jLabel281);
        jLabel281.setBounds(60, 150, 300, 60);

        jLabel282.setFont(jLabel282.getFont().deriveFont(jLabel282.getFont().getStyle() | java.awt.Font.BOLD, jLabel282.getFont().getSize()+36));
        jLabel282.setForeground(new java.awt.Color(255, 0, 51));
        jLabel282.setText(bundle.getString("CrInstrument.jLabel282.text")); 
        jPanel19.add(jLabel282);
        jLabel282.setBounds(60, 150, 300, 60);

        jLabel283.setFont(jLabel283.getFont().deriveFont(jLabel283.getFont().getStyle() | java.awt.Font.BOLD, jLabel283.getFont().getSize()+36));
        jLabel283.setForeground(new java.awt.Color(255, 0, 51));
        jLabel283.setText(bundle.getString("CrInstrument.jLabel283.text")); 
        jPanel19.add(jLabel283);
        jLabel283.setBounds(60, 150, 300, 60);

        jLabel284.setFont(jLabel284.getFont().deriveFont(jLabel284.getFont().getStyle() | java.awt.Font.BOLD, jLabel284.getFont().getSize()+36));
        jLabel284.setForeground(new java.awt.Color(255, 0, 51));
        jLabel284.setText(bundle.getString("CrInstrument.jLabel284.text")); 
        jPanel19.add(jLabel284);
        jLabel284.setBounds(60, 150, 300, 60);

        jLabel285.setFont(jLabel285.getFont().deriveFont(jLabel285.getFont().getStyle() | java.awt.Font.BOLD, jLabel285.getFont().getSize()+36));
        jLabel285.setForeground(new java.awt.Color(255, 0, 51));
        jLabel285.setText(bundle.getString("CrInstrument.jLabel285.text")); 
        jPanel19.add(jLabel285);
        jLabel285.setBounds(60, 150, 300, 60);

        jLabel286.setFont(jLabel286.getFont().deriveFont(jLabel286.getFont().getStyle() | java.awt.Font.BOLD, jLabel286.getFont().getSize()+36));
        jLabel286.setForeground(new java.awt.Color(255, 0, 51));
        jLabel286.setText(bundle.getString("CrInstrument.jLabel286.text")); 
        jPanel19.add(jLabel286);
        jLabel286.setBounds(60, 150, 300, 60);

        jLabel287.setFont(jLabel287.getFont().deriveFont(jLabel287.getFont().getStyle() | java.awt.Font.BOLD, jLabel287.getFont().getSize()+36));
        jLabel287.setForeground(new java.awt.Color(255, 0, 51));
        jLabel287.setText(bundle.getString("CrInstrument.jLabel287.text")); 
        jPanel19.add(jLabel287);
        jLabel287.setBounds(60, 150, 300, 60);

        jLabel288.setFont(jLabel288.getFont().deriveFont(jLabel288.getFont().getStyle() | java.awt.Font.BOLD, jLabel288.getFont().getSize()+36));
        jLabel288.setForeground(new java.awt.Color(255, 0, 51));
        jLabel288.setText(bundle.getString("CrInstrument.jLabel288.text")); 
        jPanel19.add(jLabel288);
        jLabel288.setBounds(60, 150, 300, 60);

        jLabel289.setFont(jLabel289.getFont().deriveFont(jLabel289.getFont().getStyle() | java.awt.Font.BOLD, jLabel289.getFont().getSize()+36));
        jLabel289.setForeground(new java.awt.Color(255, 0, 51));
        jLabel289.setText(bundle.getString("CrInstrument.jLabel289.text")); 
        jPanel19.add(jLabel289);
        jLabel289.setBounds(60, 150, 300, 60);

        jLabel290.setFont(jLabel290.getFont().deriveFont(jLabel290.getFont().getStyle() | java.awt.Font.BOLD, jLabel290.getFont().getSize()+36));
        jLabel290.setForeground(new java.awt.Color(255, 0, 51));
        jLabel290.setText(bundle.getString("CrInstrument.jLabel290.text")); 
        jPanel19.add(jLabel290);
        jLabel290.setBounds(60, 150, 300, 60);

        jLabel291.setFont(jLabel291.getFont().deriveFont(jLabel291.getFont().getStyle() | java.awt.Font.BOLD, jLabel291.getFont().getSize()+36));
        jLabel291.setForeground(new java.awt.Color(255, 0, 51));
        jLabel291.setText(bundle.getString("CrInstrument.jLabel291.text")); 
        jPanel19.add(jLabel291);
        jLabel291.setBounds(60, 150, 300, 60);

        jLabel292.setFont(jLabel292.getFont().deriveFont(jLabel292.getFont().getStyle() | java.awt.Font.BOLD, jLabel292.getFont().getSize()+36));
        jLabel292.setForeground(new java.awt.Color(255, 0, 51));
        jLabel292.setText(bundle.getString("CrInstrument.jLabel292.text")); 
        jPanel19.add(jLabel292);
        jLabel292.setBounds(60, 150, 300, 60);

        jLabel293.setFont(jLabel293.getFont().deriveFont(jLabel293.getFont().getStyle() | java.awt.Font.BOLD, jLabel293.getFont().getSize()+36));
        jLabel293.setForeground(new java.awt.Color(255, 0, 51));
        jLabel293.setText(bundle.getString("CrInstrument.jLabel293.text")); 
        jPanel19.add(jLabel293);
        jLabel293.setBounds(60, 150, 300, 60);

        jLabel294.setFont(jLabel294.getFont().deriveFont(jLabel294.getFont().getStyle() | java.awt.Font.BOLD, jLabel294.getFont().getSize()+36));
        jLabel294.setForeground(new java.awt.Color(255, 0, 51));
        jLabel294.setText(bundle.getString("CrInstrument.jLabel294.text")); 
        jPanel19.add(jLabel294);
        jLabel294.setBounds(60, 150, 300, 60);

        jLabel295.setFont(jLabel295.getFont().deriveFont(jLabel295.getFont().getStyle() | java.awt.Font.BOLD, jLabel295.getFont().getSize()+36));
        jLabel295.setForeground(new java.awt.Color(255, 0, 51));
        jLabel295.setText(bundle.getString("CrInstrument.jLabel295.text")); 
        jPanel19.add(jLabel295);
        jLabel295.setBounds(60, 150, 300, 60);

        jLabel296.setFont(jLabel296.getFont().deriveFont(jLabel296.getFont().getStyle() | java.awt.Font.BOLD, jLabel296.getFont().getSize()+36));
        jLabel296.setForeground(new java.awt.Color(255, 0, 51));
        jLabel296.setText(bundle.getString("CrInstrument.jLabel296.text")); 
        jPanel19.add(jLabel296);
        jLabel296.setBounds(60, 150, 300, 60);

        jLabel297.setFont(jLabel297.getFont().deriveFont(jLabel297.getFont().getStyle() | java.awt.Font.BOLD, jLabel297.getFont().getSize()+36));
        jLabel297.setForeground(new java.awt.Color(255, 0, 51));
        jLabel297.setText(bundle.getString("CrInstrument.jLabel297.text")); 
        jPanel19.add(jLabel297);
        jLabel297.setBounds(60, 150, 300, 60);

        jLabel298.setFont(jLabel298.getFont().deriveFont(jLabel298.getFont().getStyle() | java.awt.Font.BOLD, jLabel298.getFont().getSize()+36));
        jLabel298.setForeground(new java.awt.Color(255, 0, 51));
        jLabel298.setText(bundle.getString("CrInstrument.jLabel298.text")); 
        jPanel19.add(jLabel298);
        jLabel298.setBounds(60, 150, 300, 60);

        jLabel299.setFont(jLabel299.getFont().deriveFont(jLabel299.getFont().getStyle() | java.awt.Font.BOLD, jLabel299.getFont().getSize()+36));
        jLabel299.setForeground(new java.awt.Color(255, 0, 51));
        jLabel299.setText(bundle.getString("CrInstrument.jLabel299.text")); 
        jPanel19.add(jLabel299);
        jLabel299.setBounds(60, 150, 300, 60);

        jLabel300.setFont(jLabel300.getFont().deriveFont(jLabel300.getFont().getStyle() | java.awt.Font.BOLD, jLabel300.getFont().getSize()+36));
        jLabel300.setForeground(new java.awt.Color(255, 0, 51));
        jLabel300.setText(bundle.getString("CrInstrument.jLabel300.text")); 
        jPanel19.add(jLabel300);
        jLabel300.setBounds(60, 150, 300, 60);

        jLabel301.setFont(jLabel301.getFont().deriveFont(jLabel301.getFont().getStyle() | java.awt.Font.BOLD, jLabel301.getFont().getSize()+36));
        jLabel301.setForeground(new java.awt.Color(255, 0, 51));
        jLabel301.setText(bundle.getString("CrInstrument.jLabel301.text")); 
        jPanel19.add(jLabel301);
        jLabel301.setBounds(60, 150, 300, 60);

        jLabel302.setFont(jLabel302.getFont().deriveFont(jLabel302.getFont().getStyle() | java.awt.Font.BOLD, jLabel302.getFont().getSize()+36));
        jLabel302.setForeground(new java.awt.Color(255, 0, 51));
        jLabel302.setText(bundle.getString("CrInstrument.jLabel302.text")); 
        jPanel19.add(jLabel302);
        jLabel302.setBounds(60, 150, 300, 60);

        jLabel303.setFont(jLabel303.getFont().deriveFont(jLabel303.getFont().getStyle() | java.awt.Font.BOLD, jLabel303.getFont().getSize()+36));
        jLabel303.setForeground(new java.awt.Color(255, 0, 51));
        jLabel303.setText(bundle.getString("CrInstrument.jLabel303.text")); 
        jPanel19.add(jLabel303);
        jLabel303.setBounds(60, 150, 300, 60);

        jLabel304.setFont(jLabel304.getFont().deriveFont(jLabel304.getFont().getStyle() | java.awt.Font.BOLD, jLabel304.getFont().getSize()+36));
        jLabel304.setForeground(new java.awt.Color(255, 0, 51));
        jLabel304.setText(bundle.getString("CrInstrument.jLabel304.text")); 
        jPanel19.add(jLabel304);
        jLabel304.setBounds(60, 150, 300, 60);

        jLabel305.setFont(jLabel305.getFont().deriveFont(jLabel305.getFont().getStyle() | java.awt.Font.BOLD, jLabel305.getFont().getSize()+36));
        jLabel305.setForeground(new java.awt.Color(255, 0, 51));
        jLabel305.setText(bundle.getString("CrInstrument.jLabel305.text")); 
        jPanel19.add(jLabel305);
        jLabel305.setBounds(60, 150, 300, 60);

        jLabel306.setFont(jLabel306.getFont().deriveFont(jLabel306.getFont().getStyle() | java.awt.Font.BOLD, jLabel306.getFont().getSize()+36));
        jLabel306.setForeground(new java.awt.Color(255, 0, 51));
        jLabel306.setText(bundle.getString("CrInstrument.jLabel306.text")); 
        jPanel19.add(jLabel306);
        jLabel306.setBounds(60, 150, 300, 60);

        jLabel307.setFont(jLabel307.getFont().deriveFont(jLabel307.getFont().getStyle() | java.awt.Font.BOLD, jLabel307.getFont().getSize()+36));
        jLabel307.setForeground(new java.awt.Color(255, 0, 51));
        jLabel307.setText(bundle.getString("CrInstrument.jLabel307.text")); 
        jPanel19.add(jLabel307);
        jLabel307.setBounds(60, 150, 300, 60);

        jLabel308.setFont(jLabel308.getFont().deriveFont(jLabel308.getFont().getStyle() | java.awt.Font.BOLD, jLabel308.getFont().getSize()+36));
        jLabel308.setForeground(new java.awt.Color(255, 0, 51));
        jLabel308.setText(bundle.getString("CrInstrument.jLabel308.text")); 
        jPanel19.add(jLabel308);
        jLabel308.setBounds(60, 150, 300, 60);

        jLabel309.setFont(jLabel309.getFont().deriveFont(jLabel309.getFont().getStyle() | java.awt.Font.BOLD, jLabel309.getFont().getSize()+36));
        jLabel309.setForeground(new java.awt.Color(255, 0, 51));
        jLabel309.setText(bundle.getString("CrInstrument.jLabel309.text")); 
        jPanel19.add(jLabel309);
        jLabel309.setBounds(60, 150, 300, 60);

        jLabel310.setFont(jLabel310.getFont().deriveFont(jLabel310.getFont().getStyle() | java.awt.Font.BOLD, jLabel310.getFont().getSize()+36));
        jLabel310.setForeground(new java.awt.Color(255, 0, 51));
        jLabel310.setText(bundle.getString("CrInstrument.jLabel310.text")); 
        jPanel19.add(jLabel310);
        jLabel310.setBounds(60, 150, 300, 60);

        jPanel1.add(jPanel19);
        jPanel19.setBounds(170, 330, 380, 280);

        button02.setFont(button02.getFont().deriveFont(button02.getFont().getStyle() | java.awt.Font.BOLD));
        button02.setText(bundle.getString("CrInstrument.button02.text")); 
        button02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button02ActionPerformed(evt);
            }
        });
        jPanel1.add(button02);
        button02.setBounds(20, 410, 120, 40);

        btnStart.setFont(btnStart.getFont().deriveFont(btnStart.getFont().getStyle() | java.awt.Font.BOLD));
        btnStart.setText(bundle.getString("CrInstrument.btnStart.text")); 
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jPanel1.add(btnStart);
        btnStart.setBounds(20, 460, 120, 40);

        jLabel45.setFont(new java.awt.Font("Arial", 1, 12)); 
        jLabel45.setText(bundle.getString("CrInstrument.jLabel45.text")); 
        jPanel1.add(jLabel45);
        jLabel45.setBounds(30, 520, 130, 20);

        chartOptionPanel.setBackground(new java.awt.Color(255, 255, 255));
        chartOptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true), bundle.getString("CrInstrument.chartOptionPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(51, 51, 51))); 
        chartOptionPanel.setFont(chartOptionPanel.getFont());
        chartOptionPanel.setLayout(new java.awt.GridLayout(3, 1));

        jPanel43.setOpaque(false);

        cbAutoAdjustY.setFont(cbAutoAdjustY.getFont());
        cbAutoAdjustY.setText(bundle.getString("CrInstrument.cbAutoAdjustY.text")); 
        cbAutoAdjustY.setOpaque(false);
        cbAutoAdjustY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAutoAdjustYActionPerformed(evt);
            }
        });
        jPanel43.add(cbAutoAdjustY);

        chartOptionPanel.add(jPanel43);

        jPanel45.setOpaque(false);

        cbZero.setFont(cbZero.getFont());
        cbZero.setText(bundle.getString("CrInstrument.cbZero.text")); 
        cbZero.setOpaque(false);
        cbZero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbZeroActionPerformed(evt);
            }
        });
        jPanel45.add(cbZero);

        cbRemark.setFont(cbRemark.getFont());
        cbRemark.setText(bundle.getString("CrInstrument.cbRemark.text")); 
        cbRemark.setOpaque(false);
        jPanel45.add(cbRemark);

        chartOptionPanel.add(jPanel45);

        jPanel46.setOpaque(false);

        showAlarmRB.setBackground(new java.awt.Color(255, 255, 255));
        showAlarmRB.setFont(showAlarmRB.getFont());
        showAlarmRB.setText(bundle.getString("CrInstrument.showAlarmRB.text")); 
        showAlarmRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAlarmRBActionPerformed(evt);
            }
        });
        jPanel46.add(showAlarmRB);

        btnZoomIn.setFont(btnZoomIn.getFont());
        btnZoomIn.setText(bundle.getString("CrInstrument.btnZoomIn.text")); 
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });
        jPanel46.add(btnZoomIn);

        btnZoomOut.setFont(btnZoomOut.getFont());
        btnZoomOut.setText(bundle.getString("CrInstrument.btnZoomOut.text")); 
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });
        jPanel46.add(btnZoomOut);

        chartOptionPanel.add(jPanel46);

        jPanel1.add(chartOptionPanel);
        chartOptionPanel.setBounds(560, 480, 440, 130);

        btnConnect.setFont(btnConnect.getFont().deriveFont(btnConnect.getFont().getStyle() | java.awt.Font.BOLD));
        btnConnect.setText(bundle.getString("CrInstrument.btnConnect.text")); 
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        jPanel1.add(btnConnect);
        btnConnect.setBounds(20, 360, 120, 40);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); 
        jLabel13.setText(bundle.getString("CrInstrument.jLabel13.text")); 
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 320, 120, 15);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new java.awt.GridLayout(2, 1));

        jLabel58.setText(bundle.getString("CrInstrument.jLabel58.text")); 
        jPanel37.add(jLabel58);

        jLabel76.setText(bundle.getString("CrInstrument.jLabel76.text")); 
        jPanel37.add(jLabel76);

        jPanel1.add(jPanel37);
        jPanel37.setBounds(10, 200, 140, 50);

        javax.swing.GroupLayout lightPanel2Layout = new javax.swing.GroupLayout(lightPanel2);
        lightPanel2.setLayout(lightPanel2Layout);
        lightPanel2Layout.setHorizontalGroup(
            lightPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        lightPanel2Layout.setVerticalGroup(
            lightPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(lightPanel2);
        lightPanel2.setBounds(30, 550, 120, 50);

        button03.setFont(button03.getFont());
        button03.setText(bundle.getString("CrInstrument.button03.text")); 
        button03.setActionCommand(bundle.getString("CrInstrument.button03.actionCommand")); 
        button03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button03ActionPerformed(evt);
            }
        });
        jPanel1.add(button03);
        button03.setBounds(20, 260, 120, 40);

        button04.setFont(button04.getFont());
        button04.setText(bundle.getString("CrInstrument.button04.text")); 
        button04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button04ActionPerformed(evt);
            }
        });
        jPanel1.add(button04);
        button04.setBounds(20, 260, 120, 40);

        button05.setFont(button05.getFont());
        button05.setText(bundle.getString("CrInstrument.button05.text")); 
        button05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button05ActionPerformed(evt);
            }
        });
        jPanel1.add(button05);
        button05.setBounds(20, 260, 120, 40);

        button06.setFont(button06.getFont());
        button06.setText(bundle.getString("CrInstrument.button06.text")); 
        button06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button06ActionPerformed(evt);
            }
        });
        jPanel1.add(button06);
        button06.setBounds(20, 260, 120, 40);

        button07.setFont(button07.getFont());
        button07.setText(bundle.getString("CrInstrument.button07.text")); 
        button07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button07ActionPerformed(evt);
            }
        });
        jPanel1.add(button07);
        button07.setBounds(20, 260, 120, 40);

        button08.setFont(button08.getFont());
        button08.setText(bundle.getString("CrInstrument.button08.text")); 
        button08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button08ActionPerformed(evt);
            }
        });
        jPanel1.add(button08);
        button08.setBounds(20, 260, 120, 40);

        button09.setFont(button09.getFont());
        button09.setText(bundle.getString("CrInstrument.button09.text")); 
        button09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button09ActionPerformed(evt);
            }
        });
        jPanel1.add(button09);
        button09.setBounds(20, 260, 120, 40);

        button10.setFont(button10.getFont());
        button10.setText(bundle.getString("CrInstrument.button10.text")); 
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        jPanel1.add(button10);
        button10.setBounds(20, 260, 120, 40);

        jTabbedPane1.addTab(bundle.getString("CrInstrument.jPanel1.TabConstraints.tabTitle"), jPanel1); 

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTabbedPane2.setFont(jTabbedPane2.getFont());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setFont(jPanel4.getFont());
        jPanel4.setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(jTable1.getFont());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Station", "Device name", "Model", "SN", "Data name", "Decimal number", "Unit", "Master ID", "Device ID", "Place", "Dummy Code", "Is dummy", "上警戒值", "下警戒值", "上行動值", "下行動值"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 1014, 280);

        jButton23.setFont(jButton23.getFont().deriveFont(jButton23.getFont().getSize()+12f));
        jButton23.setText(bundle.getString("CrInstrument.jButton23.text")); 
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton23);
        jButton23.setBounds(740, 360, 240, 60);

        btnEditSrc.setFont(btnEditSrc.getFont().deriveFont(btnEditSrc.getFont().getSize()+12f));
        btnEditSrc.setText(bundle.getString("CrInstrument.btnEditSrc.text")); 
        btnEditSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSrcActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditSrc);
        btnEditSrc.setBounds(180, 360, 290, 60);

        jButton1.setFont(jButton1.getFont());
        jButton1.setText(bundle.getString("CrInstrument.jButton1.text")); 
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(20, 290, 170, 23);

        jButton4.setFont(jButton4.getFont());
        jButton4.setText(bundle.getString("CrInstrument.jButton4.text")); 
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);
        jButton4.setBounds(220, 290, 230, 23);

        jButton6.setFont(jButton6.getFont());
        jButton6.setText(bundle.getString("CrInstrument.jButton6.text")); 
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);
        jButton6.setBounds(480, 290, 120, 23);

        jButton5.setFont(jButton5.getFont().deriveFont(jButton5.getFont().getSize()+12f));
        jButton5.setText(bundle.getString("CrInstrument.jButton5.text")); 
        jPanel4.add(jButton5);
        jButton5.setBounds(500, 360, 220, 60);

        jTabbedPane2.addTab(bundle.getString("CrInstrument.jPanel4.TabConstraints.tabTitle"), jPanel4); 

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setFont(jPanel5.getFont());
        jPanel5.setLayout(null);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel9.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 153, 153))); 
        jPanel9.setFont(jPanel9.getFont());
        jPanel9.setLayout(null);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Port Name", "Station Name"
            }
        ));
        jScrollPane20.setViewportView(jTable6);

        jPanel9.add(jScrollPane20);
        jScrollPane20.setBounds(20, 30, 290, 100);

        jButton27.setText(bundle.getString("CrInstrument.jButton27.text")); 
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton27);
        jButton27.setBounds(40, 140, 90, 23);

        jButton43.setText(bundle.getString("CrInstrument.jButton43.text")); 
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton43);
        jButton43.setBounds(150, 140, 130, 23);

        jPanel5.add(jPanel9);
        jPanel9.setBounds(390, 30, 330, 180);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel11.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 153, 153))); 
        jPanel11.setFont(jPanel11.getFont());
        jPanel11.setLayout(null);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Port Name", "Station Name"
            }
        ));
        jScrollPane19.setViewportView(jTable5);

        jPanel11.add(jScrollPane19);
        jScrollPane19.setBounds(20, 30, 320, 100);

        jButton19.setText(bundle.getString("CrInstrument.jButton19.text")); 
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton19);
        jButton19.setBounds(50, 140, 100, 23);

        jButton26.setText(bundle.getString("CrInstrument.jButton26.text")); 
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton26);
        jButton26.setBounds(170, 140, 130, 23);

        jPanel5.add(jPanel11);
        jPanel11.setBounds(18, 28, 360, 180);

        jButton7.setFont(jButton7.getFont().deriveFont(jButton7.getFont().getSize()+12f));
        jButton7.setText(bundle.getString("CrInstrument.jButton7.text")); 
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);
        jButton7.setBounds(740, 30, 240, 50);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel15.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 153, 153))); 
        jPanel15.setFont(jPanel15.getFont());
        jPanel15.setLayout(null);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel26.setFont(jLabel26.getFont());
        jLabel26.setText(bundle.getString("CrInstrument.jLabel26.text")); 
        jPanel16.add(jLabel26);

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        jPanel16.add(jComboBox11);

        jLabel28.setFont(jLabel28.getFont());
        jLabel28.setText(bundle.getString("CrInstrument.jLabel28.text")); 
        jPanel16.add(jLabel28);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel16.add(jComboBox7);

        jLabel29.setFont(jLabel29.getFont());
        jLabel29.setText(bundle.getString("CrInstrument.jLabel29.text")); 
        jPanel16.add(jLabel29);

        jPanel15.add(jPanel16);
        jPanel16.setBounds(10, 19, 310, 31);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel27.setFont(jLabel27.getFont());
        jLabel27.setText(bundle.getString("CrInstrument.jLabel27.text")); 
        jPanel17.add(jLabel27);

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        jPanel17.add(jComboBox12);

        jLabel32.setFont(jLabel32.getFont());
        jLabel32.setText(bundle.getString("CrInstrument.jLabel32.text")); 
        jPanel17.add(jLabel32);

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel17.add(jComboBox13);

        jLabel33.setFont(jLabel33.getFont());
        jLabel33.setText(bundle.getString("CrInstrument.jLabel33.text")); 
        jPanel17.add(jLabel33);

        jPanel15.add(jPanel17);
        jPanel17.setBounds(10, 50, 310, 40);

        jPanel5.add(jPanel15);
        jPanel15.setBounds(20, 370, 380, 110);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel10.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 153, 153))); 
        jPanel10.setFont(jPanel10.getFont());
        jPanel10.setLayout(null);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel14.setFont(jLabel14.getFont());
        jLabel14.setText(bundle.getString("CrInstrument.jLabel14.text")); 
        jPanel34.add(jLabel14);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(jCheckBox1.getFont());
        jCheckBox1.setText(bundle.getString("CrInstrument.jCheckBox1.text")); 
        jPanel34.add(jCheckBox1);

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setFont(jCheckBox2.getFont());
        jCheckBox2.setText(bundle.getString("CrInstrument.jCheckBox2.text")); 
        jPanel34.add(jCheckBox2);

        jPanel10.add(jPanel34);
        jPanel34.setBounds(10, 20, 350, 30);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel74.setFont(jLabel74.getFont());
        jLabel74.setText(bundle.getString("CrInstrument.jLabel74.text")); 
        jPanel38.add(jLabel74);

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setFont(jCheckBox17.getFont());
        jCheckBox17.setText(bundle.getString("CrInstrument.jCheckBox17.text")); 
        jPanel38.add(jCheckBox17);

        jCheckBox18.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox18.setFont(jCheckBox18.getFont());
        jCheckBox18.setText(bundle.getString("CrInstrument.jCheckBox18.text")); 
        jPanel38.add(jCheckBox18);

        jPanel10.add(jPanel38);
        jPanel38.setBounds(10, 60, 350, 30);

        jPanel5.add(jPanel10);
        jPanel10.setBounds(20, 230, 380, 110);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel22.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 153, 153))); 
        jPanel22.setFont(jPanel22.getFont());
        jPanel22.setLayout(null);

        jTable4.setFont(jTable4.getFont());
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "請輸入收訊的E-Mail帳號"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        jPanel22.add(jScrollPane5);
        jScrollPane5.setBounds(10, 20, 280, 80);

        jButton16.setFont(jButton16.getFont());
        jButton16.setText(bundle.getString("CrInstrument.jButton16.text")); 
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton16);
        jButton16.setBounds(50, 110, 80, 23);

        jButton20.setFont(jButton20.getFont());
        jButton20.setText(bundle.getString("CrInstrument.jButton20.text")); 
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton20);
        jButton20.setBounds(150, 110, 110, 23);

        jPanel5.add(jPanel22);
        jPanel22.setBounds(420, 230, 300, 140);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel21.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 153, 153))); 
        jPanel21.setFont(jPanel21.getFont());
        jPanel21.setLayout(null);

        jTable3.setFont(jTable3.getFont());
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "請輸入收訊的手機號碼"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        jPanel21.add(jScrollPane4);
        jScrollPane4.setBounds(10, 20, 280, 80);

        jButton21.setFont(jButton21.getFont());
        jButton21.setText(bundle.getString("CrInstrument.jButton21.text")); 
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton21);
        jButton21.setBounds(50, 110, 80, 23);

        jButton22.setFont(jButton22.getFont());
        jButton22.setText(bundle.getString("CrInstrument.jButton22.text")); 
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton22);
        jButton22.setBounds(150, 110, 110, 23);

        jPanel5.add(jPanel21);
        jPanel21.setBounds(420, 390, 300, 140);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel60.setText(bundle.getString("CrInstrument.jLabel60.text")); 
        jPanel29.add(jLabel60);

        jComboBox21.setEditable(true);
        jComboBox21.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel29.add(jComboBox21);

        jLabel61.setText(bundle.getString("CrInstrument.jLabel61.text")); 
        jPanel29.add(jLabel61);

        jTextField22.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel29.add(jTextField22);

        jPanel5.add(jPanel29);
        jPanel29.setBounds(680, 120, 330, 40);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel62.setText(bundle.getString("CrInstrument.jLabel62.text")); 
        jPanel30.add(jLabel62);

        jComboBox22.setEditable(true);
        jComboBox22.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel30.add(jComboBox22);

        jLabel63.setText(bundle.getString("CrInstrument.jLabel63.text")); 
        jPanel30.add(jLabel63);

        jTextField23.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel30.add(jTextField23);

        jPanel5.add(jPanel30);
        jPanel30.setBounds(670, 160, 330, 35);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setText(bundle.getString("CrInstrument.jLabel11.text")); 
        jPanel28.add(jLabel11);

        jComboBox15.setEditable(true);
        jComboBox15.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel28.add(jComboBox15);

        jLabel59.setText(bundle.getString("CrInstrument.jLabel59.text")); 
        jPanel28.add(jLabel59);

        jTextField11.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel28.add(jTextField11);

        jPanel5.add(jPanel28);
        jPanel28.setBounds(670, 200, 330, 35);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel65.setText(bundle.getString("CrInstrument.jLabel65.text")); 
        jPanel31.add(jLabel65);

        jComboBox23.setEditable(true);
        jComboBox23.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel31.add(jComboBox23);

        jLabel66.setText(bundle.getString("CrInstrument.jLabel66.text")); 
        jPanel31.add(jLabel66);

        jTextField24.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel31.add(jTextField24);

        jPanel5.add(jPanel31);
        jPanel31.setBounds(700, 250, 300, 35);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel67.setText(bundle.getString("CrInstrument.jLabel67.text")); 
        jPanel32.add(jLabel67);

        jComboBox24.setEditable(true);
        jComboBox24.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel32.add(jComboBox24);

        jLabel68.setText(bundle.getString("CrInstrument.jLabel68.text")); 
        jPanel32.add(jLabel68);

        jTextField25.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel32.add(jTextField25);

        jPanel5.add(jPanel32);
        jPanel32.setBounds(690, 290, 300, 35);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel69.setText(bundle.getString("CrInstrument.jLabel69.text")); 
        jPanel33.add(jLabel69);

        jComboBox25.setEditable(true);
        jComboBox25.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel33.add(jComboBox25);

        jLabel70.setText(bundle.getString("CrInstrument.jLabel70.text")); 
        jPanel33.add(jLabel70);

        jTextField26.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel33.add(jTextField26);

        jPanel5.add(jPanel33);
        jPanel33.setBounds(690, 330, 300, 40);

        jTabbedPane2.addTab(bundle.getString("CrInstrument.jPanel5.TabConstraints.tabTitle"), jPanel5); 

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setFont(jPanel20.getFont());
        jPanel20.setLayout(null);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel24.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(102, 0, 153))); 
        jPanel24.setFont(jPanel24.getFont());
        jPanel24.setLayout(null);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel53.setFont(jLabel53.getFont());
        jLabel53.setText(bundle.getString("CrInstrument.jLabel53.text")); 
        jPanel25.add(jLabel53);

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        jPanel25.add(jComboBox17);

        jLabel50.setFont(jLabel50.getFont());
        jLabel50.setText(bundle.getString("CrInstrument.jLabel50.text")); 
        jPanel25.add(jLabel50);

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel25.add(jComboBox8);

        jLabel51.setFont(jLabel51.getFont());
        jLabel51.setText(bundle.getString("CrInstrument.jLabel51.text")); 
        jPanel25.add(jLabel51);

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel25.add(jComboBox9);

        jLabel52.setFont(jLabel52.getFont());
        jLabel52.setText(bundle.getString("CrInstrument.jLabel52.text")); 
        jPanel25.add(jLabel52);

        jPanel24.add(jPanel25);
        jPanel25.setBounds(10, 310, 630, 40);

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));
        jPanel64.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(jLabel1.getFont());
        jLabel1.setText(bundle.getString("CrInstrument.jLabel1.text")); 
        jPanel64.add(jLabel1);

        jTextField1.setFont(jTextField1.getFont());
        jTextField1.setPreferredSize(new java.awt.Dimension(250, 25));
        jPanel64.add(jTextField1);

        jButton3.setFont(jButton3.getFont());
        jButton3.setText(bundle.getString("CrInstrument.jButton3.text")); 
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel64.add(jButton3);

        jPanel24.add(jPanel64);
        jPanel64.setBounds(10, 60, 600, 35);

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));
        jPanel65.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel17.setFont(jLabel17.getFont());
        jLabel17.setText(bundle.getString("CrInstrument.jLabel17.text")); 
        jPanel65.add(jLabel17);

        jTextField8.setFont(jTextField8.getFont());
        jTextField8.setPreferredSize(new java.awt.Dimension(130, 25));
        jPanel65.add(jTextField8);

        jLabel5.setFont(jLabel5.getFont());
        jLabel5.setText(bundle.getString("CrInstrument.jLabel5.text")); 
        jPanel65.add(jLabel5);

        jTextField7.setFont(jTextField7.getFont());
        jTextField7.setPreferredSize(new java.awt.Dimension(130, 25));
        jPanel65.add(jTextField7);

        jPanel24.add(jPanel65);
        jPanel65.setBounds(10, 100, 650, 40);

        jButton14.setFont(jButton14.getFont());
        jButton14.setText(bundle.getString("CrInstrument.jButton14.text")); 
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton14);
        jButton14.setBounds(300, 360, 220, 23);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setFont(jRadioButton6.getFont());
        jRadioButton6.setText(bundle.getString("CrInstrument.jRadioButton6.text")); 
        jRadioButton6.setOpaque(false);
        jPanel35.add(jRadioButton6);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        jPanel35.add(jComboBox4);

        jLabel18.setFont(jLabel18.getFont());
        jLabel18.setText(bundle.getString("CrInstrument.jLabel18.text")); 
        jPanel35.add(jLabel18);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel35.add(jComboBox5);

        jLabel21.setFont(jLabel21.getFont());
        jLabel21.setText(bundle.getString("CrInstrument.jLabel21.text")); 
        jPanel35.add(jLabel21);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel35.add(jComboBox6);

        jLabel71.setFont(jLabel71.getFont());
        jLabel71.setText(bundle.getString("CrInstrument.jLabel71.text")); 
        jPanel35.add(jLabel71);

        jPanel24.add(jPanel35);
        jPanel35.setBounds(50, 270, 580, 33);

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel38.setFont(jLabel38.getFont());
        jLabel38.setText(bundle.getString("CrInstrument.jLabel38.text")); 
        jPanel66.add(jLabel38);

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*.txt", "*.csv", "*.dat" }));
        jPanel66.add(jComboBox10);

        jPanel24.add(jPanel66);
        jPanel66.setBounds(10, 150, 500, 40);

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox25.setFont(jCheckBox25.getFont());
        jCheckBox25.setText(bundle.getString("CrInstrument.jCheckBox25.text")); 
        jCheckBox25.setOpaque(false);
        jPanel12.add(jCheckBox25);

        jCheckBox48.setFont(jCheckBox48.getFont());
        jCheckBox48.setText(bundle.getString("CrInstrument.jCheckBox48.text")); 
        jCheckBox48.setOpaque(false);
        jPanel12.add(jCheckBox48);

        jPanel24.add(jPanel12);
        jPanel12.setBounds(10, 20, 510, 30);

        jPanel14.setOpaque(false);
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel77.setFont(jLabel77.getFont());
        jLabel77.setText(bundle.getString("CrInstrument.jLabel77.text")); 
        jPanel14.add(jLabel77);

        buttonGroup3.add(jRadioButton1);
        jRadioButton1.setFont(jRadioButton1.getFont());
        jRadioButton1.setText(bundle.getString("CrInstrument.jRadioButton1.text")); 
        jRadioButton1.setOpaque(false);
        jPanel14.add(jRadioButton1);

        jPanel24.add(jPanel14);
        jPanel14.setBounds(10, 230, 530, 30);

        jPanel42.setOpaque(false);
        jPanel42.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel125.setFont(jLabel125.getFont());
        jLabel125.setText(bundle.getString("CrInstrument.jLabel125.text")); 
        jPanel42.add(jLabel125);

        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setFont(jRadioButton7.getFont());
        jRadioButton7.setText(bundle.getString("CrInstrument.jRadioButton7.text")); 
        jRadioButton7.setOpaque(false);
        jPanel42.add(jRadioButton7);

        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setFont(jRadioButton8.getFont());
        jRadioButton8.setText(bundle.getString("CrInstrument.jRadioButton8.text")); 
        jRadioButton8.setOpaque(false);
        jPanel42.add(jRadioButton8);

        jPanel24.add(jPanel42);
        jPanel42.setBounds(10, 190, 440, 40);

        jPanel108.setOpaque(false);
        jPanel108.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel126.setFont(jLabel126.getFont());
        jLabel126.setText(bundle.getString("CrInstrument.jLabel126.text")); 
        jPanel108.add(jLabel126);

        jTextField39.setText(bundle.getString("CrInstrument.jTextField39.text")); 
        jTextField39.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel108.add(jTextField39);

        jLabel127.setText(bundle.getString("CrInstrument.jLabel127.text")); 
        jPanel108.add(jLabel127);

        jPanel24.add(jPanel108);
        jPanel108.setBounds(20, 350, 250, 40);

        jPanel20.add(jPanel24);
        jPanel24.setBounds(30, 140, 670, 400);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel27.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(102, 0, 153))); 
        jPanel27.setFont(jPanel27.getFont());
        jPanel27.setLayout(null);

        jCheckBox10.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox10.setFont(jCheckBox10.getFont());
        jCheckBox10.setText(bundle.getString("CrInstrument.jCheckBox10.text")); 
        jPanel27.add(jCheckBox10);
        jCheckBox10.setBounds(10, 20, 280, 23);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel48.setFont(jLabel48.getFont());
        jLabel48.setText(bundle.getString("CrInstrument.jLabel48.text")); 
        jPanel23.add(jLabel48);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        jPanel23.add(jComboBox1);

        jLabel10.setFont(jLabel10.getFont());
        jLabel10.setText(bundle.getString("CrInstrument.jLabel10.text")); 
        jPanel23.add(jLabel10);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel23.add(jComboBox2);

        jLabel12.setFont(jLabel12.getFont());
        jLabel12.setText(bundle.getString("CrInstrument.jLabel12.text")); 
        jPanel23.add(jLabel12);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel23.add(jComboBox3);

        jLabel49.setFont(jLabel49.getFont());
        jLabel49.setText(bundle.getString("CrInstrument.jLabel49.text")); 
        jPanel23.add(jLabel49);

        jPanel27.add(jPanel23);
        jPanel23.setBounds(10, 60, 480, 40);

        CBUseEngineerUnit.setBackground(new java.awt.Color(255, 255, 255));
        CBUseEngineerUnit.setFont(CBUseEngineerUnit.getFont());
        CBUseEngineerUnit.setText(bundle.getString("CrInstrument.CBUseEngineerUnit.text")); 
        jPanel27.add(CBUseEngineerUnit);
        CBUseEngineerUnit.setBounds(330, 20, 210, 23);

        jPanel20.add(jPanel27);
        jPanel27.setBounds(30, 20, 550, 110);

        jButton17.setFont(jButton17.getFont().deriveFont(jButton17.getFont().getSize()+12f));
        jButton17.setText(bundle.getString("CrInstrument.jButton17.text")); 
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton17);
        jButton17.setBounds(720, 30, 250, 50);

        jTabbedPane2.addTab(bundle.getString("CrInstrument.jPanel20.TabConstraints.tabTitle"), jPanel20); 

        jPanel2.add(jTabbedPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(bundle.getString("CrInstrument.jPanel2.TabConstraints.tabTitle"), jPanel2); 

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jTabbedPane3.setFont(jTabbedPane3.getFont());

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setFont(jPanel44.getFont());
        jPanel44.setLayout(null);

        btnLogoutAdmin.setFont(btnLogoutAdmin.getFont().deriveFont(btnLogoutAdmin.getFont().getSize()+12f));
        btnLogoutAdmin.setText(bundle.getString("CrInstrument.btnLogoutAdmin.text")); 
        btnLogoutAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutAdminActionPerformed(evt);
            }
        });
        jPanel44.add(btnLogoutAdmin);
        btnLogoutAdmin.setBounds(740, 30, 250, 50);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel7.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 0, 255))); 
        jPanel7.setFont(jPanel7.getFont());
        jPanel7.setLayout(null);

        jButton2.setFont(jButton2.getFont());
        jButton2.setText(bundle.getString("CrInstrument.jButton2.text")); 
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2);
        jButton2.setBounds(30, 150, 170, 30);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setFont(jLabel2.getFont());
        jLabel2.setText(bundle.getString("CrInstrument.jLabel2.text")); 
        jPanel52.add(jLabel2);

        jPasswordField1.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel52.add(jPasswordField1);

        jPanel7.add(jPanel52);
        jPanel52.setBounds(10, 20, 230, 40);

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(jLabel4.getFont());
        jLabel4.setText(bundle.getString("CrInstrument.jLabel4.text")); 
        jPanel54.add(jLabel4);

        jPasswordField3.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel54.add(jPasswordField3);

        jPanel7.add(jPanel54);
        jPanel54.setBounds(10, 100, 290, 40);

        jPanel55.setBackground(new java.awt.Color(255, 255, 255));
        jPanel55.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setFont(jLabel3.getFont());
        jLabel3.setText(bundle.getString("CrInstrument.jLabel3.text")); 
        jPanel55.add(jLabel3);

        jPasswordField2.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel55.add(jPasswordField2);

        jPanel7.add(jPanel55);
        jPanel55.setBounds(10, 60, 230, 35);

        jPanel44.add(jPanel7);
        jPanel7.setBounds(30, 30, 320, 190);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 0, 255))); 
        jPanel6.setFont(jPanel6.getFont());
        jPanel6.setLayout(null);

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));
        jPanel57.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel15.setFont(jLabel15.getFont());
        jLabel15.setText(bundle.getString("CrInstrument.jLabel15.text")); 
        jPanel57.add(jLabel15);

        jTextField6.setPreferredSize(new java.awt.Dimension(230, 25));
        jPanel57.add(jTextField6);

        jPanel6.add(jPanel57);
        jPanel57.setBounds(10, 20, 310, 35);

        btnTestEMail.setFont(btnTestEMail.getFont());
        btnTestEMail.setText(bundle.getString("CrInstrument.btnTestEMail.text")); 
        btnTestEMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestEMailActionPerformed(evt);
            }
        });
        jPanel6.add(btnTestEMail);
        btnTestEMail.setBounds(60, 110, 120, 30);

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));
        jPanel58.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel16.setFont(jLabel16.getFont());
        jLabel16.setText(bundle.getString("CrInstrument.jLabel16.text")); 
        jPanel58.add(jLabel16);

        jPasswordField7.setPreferredSize(new java.awt.Dimension(120, 25));
        jPanel58.add(jPasswordField7);

        jPanel6.add(jPanel58);
        jPanel58.setBounds(10, 60, 220, 40);
        jPanel6.add(jLabel25);
        jLabel25.setBounds(20, 160, 200, 20);

        jPanel44.add(jPanel6);
        jPanel6.setBounds(370, 30, 340, 210);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel8.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 0, 255))); 
        jPanel8.setFont(jPanel8.getFont());
        jPanel8.setLayout(null);

        btnTestSMS.setFont(btnTestSMS.getFont());
        btnTestSMS.setText(bundle.getString("CrInstrument.btnTestSMS.text")); 
        btnTestSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestSMSActionPerformed(evt);
            }
        });
        jPanel8.add(btnTestSMS);
        btnTestSMS.setBounds(60, 110, 120, 30);

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setFont(jLabel6.getFont());
        jLabel6.setText(bundle.getString("CrInstrument.jLabel6.text")); 
        jPanel56.add(jLabel6);

        jTextField2.setPreferredSize(new java.awt.Dimension(120, 25));
        jPanel56.add(jTextField2);

        jPanel8.add(jPanel56);
        jPanel56.setBounds(10, 20, 220, 35);

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel53.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel7.setFont(jLabel7.getFont());
        jLabel7.setText(bundle.getString("CrInstrument.jLabel7.text")); 
        jPanel53.add(jLabel7);

        jPasswordField5.setPreferredSize(new java.awt.Dimension(120, 25));
        jPanel53.add(jPasswordField5);

        jPanel8.add(jPanel53);
        jPanel53.setBounds(10, 60, 220, 40);
        jPanel8.add(jLabel19);
        jLabel19.setBounds(20, 160, 200, 20);

        jPanel44.add(jPanel8);
        jPanel8.setBounds(370, 260, 240, 190);

        FTPPanel.setBackground(new java.awt.Color(255, 255, 255));
        FTPPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.FTPPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 0, 204))); 
        FTPPanel.setFont(FTPPanel.getFont());
        FTPPanel.setLayout(null);

        jButton8.setFont(jButton8.getFont());
        jButton8.setText(bundle.getString("CrInstrument.jButton8.text")); 
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        FTPPanel.add(jButton8);
        jButton8.setBounds(40, 180, 110, 30);

        jButton10.setFont(jButton10.getFont());
        jButton10.setText(bundle.getString("CrInstrument.jButton10.text")); 
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        FTPPanel.add(jButton10);
        jButton10.setBounds(170, 180, 110, 30);

        jCheckBox9.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox9.setFont(jCheckBox9.getFont());
        jCheckBox9.setText(bundle.getString("CrInstrument.jCheckBox9.text")); 
        FTPPanel.add(jCheckBox9);
        jCheckBox9.setBounds(180, 60, 170, 23);

        jPanel59.setBackground(new java.awt.Color(255, 255, 255));
        jPanel59.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel40.setFont(jLabel40.getFont());
        jLabel40.setText(bundle.getString("CrInstrument.jLabel40.text")); 
        jPanel59.add(jLabel40);

        jTextField17.setText(bundle.getString("CrInstrument.jTextField17.text")); 
        jTextField17.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel59.add(jTextField17);

        FTPPanel.add(jPanel59);
        jPanel59.setBounds(10, 20, 150, 30);

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel41.setFont(jLabel41.getFont());
        jLabel41.setText(bundle.getString("CrInstrument.jLabel41.text")); 
        jPanel60.add(jLabel41);

        jTextField18.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel60.add(jTextField18);

        FTPPanel.add(jPanel60);
        jPanel60.setBounds(10, 50, 180, 35);

        jPanel61.setBackground(new java.awt.Color(255, 255, 255));
        jPanel61.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel42.setFont(jLabel42.getFont());
        jLabel42.setText(bundle.getString("CrInstrument.jLabel42.text")); 
        jPanel61.add(jLabel42);

        jPasswordField6.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel61.add(jPasswordField6);

        FTPPanel.add(jPanel61);
        jPanel61.setBounds(10, 90, 300, 40);

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));
        jPanel62.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel43.setFont(jLabel43.getFont());
        jLabel43.setText(bundle.getString("CrInstrument.jLabel43.text")); 
        jPanel62.add(jLabel43);

        jTextField19.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanel62.add(jTextField19);

        FTPPanel.add(jPanel62);
        jPanel62.setBounds(10, 130, 340, 40);

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));
        jPanel63.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel44.setFont(jLabel44.getFont());
        jLabel44.setText(bundle.getString("CrInstrument.jLabel44.text")); 
        jPanel63.add(jLabel44);

        jTextField20.setText(bundle.getString("CrInstrument.jTextField20.text")); 
        jTextField20.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel63.add(jTextField20);

        FTPPanel.add(jPanel63);
        jPanel63.setBounds(190, 20, 100, 30);

        jPanel44.add(FTPPanel);
        FTPPanel.setBounds(620, 250, 370, 220);

        onlyReceiveCB.setBackground(new java.awt.Color(255, 255, 255));
        onlyReceiveCB.setFont(onlyReceiveCB.getFont());
        onlyReceiveCB.setText(bundle.getString("CrInstrument.onlyReceiveCB.text")); 
        onlyReceiveCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlyReceiveCBActionPerformed(evt);
            }
        });
        jPanel44.add(onlyReceiveCB);
        onlyReceiveCB.setBounds(30, 450, 380, 23);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true), bundle.getString("CrInstrument.jPanel13.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("新細明體", 0, 12), new java.awt.Color(0, 0, 255))); 
        jPanel13.setFont(jPanel13.getFont());
        jPanel13.setLayout(null);

        jButton15.setFont(jButton15.getFont());
        jButton15.setText(bundle.getString("CrInstrument.jButton15.text")); 
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton15);
        jButton15.setBounds(30, 120, 170, 30);

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));
        jPanel67.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel23.setFont(jLabel23.getFont());
        jLabel23.setText(bundle.getString("CrInstrument.jLabel23.text")); 
        jPanel67.add(jLabel23);

        jPasswordField8.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel67.add(jPasswordField8);

        jPanel13.add(jPanel67);
        jPanel67.setBounds(10, 70, 300, 40);

        jPanel68.setBackground(new java.awt.Color(255, 255, 255));
        jPanel68.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel24.setFont(jLabel24.getFont());
        jLabel24.setText(bundle.getString("CrInstrument.jLabel24.text")); 
        jPanel68.add(jLabel24);

        jPasswordField9.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel68.add(jPasswordField9);

        jPanel13.add(jPanel68);
        jPanel68.setBounds(10, 30, 240, 35);

        jPanel44.add(jPanel13);
        jPanel13.setBounds(30, 230, 320, 170);

        jButton24.setFont(jButton24.getFont().deriveFont(jButton24.getFont().getSize()+12f));
        jButton24.setText(bundle.getString("CrInstrument.jButton24.text")); 
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton24);
        jButton24.setBounds(740, 100, 250, 50);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.jPanel44.TabConstraints.tabTitle"), jPanel44); 

        UIPanel.setLayout(new java.awt.BorderLayout());

        jTabbedPane4.setFont(jTabbedPane4.getFont());

        jPanel36.setFont(jPanel36.getFont());
        jPanel36.setLayout(null);

        jPanel140.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jPanel140.setLayout(new java.awt.BorderLayout());
        jPanel36.add(jPanel140);
        jPanel140.setBounds(30, 20, 460, 250);

        jButton57.setFont(jButton57.getFont().deriveFont(jButton57.getFont().getSize()+12f));
        jButton57.setText(bundle.getString("CrInstrument.jButton57.text")); 
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton57);
        jButton57.setBounds(720, 30, 260, 60);

        jButton58.setFont(jButton58.getFont().deriveFont(jButton58.getFont().getSize()+12f));
        jButton58.setText(bundle.getString("CrInstrument.jButton58.text")); 
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton58);
        jButton58.setBounds(720, 120, 260, 50);

        jPanel144.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel144.border.title"))); 
        jPanel144.setFont(jPanel144.getFont());
        jPanel144.setLayout(null);

        jPanel141.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel141.add(jComboBox36);

        jCheckBox37.setFont(jCheckBox37.getFont());
        jCheckBox37.setText(bundle.getString("CrInstrument.jCheckBox37.text")); 
        jPanel141.add(jCheckBox37);

        jLabel31.setFont(jLabel31.getFont());
        jLabel31.setText(bundle.getString("CrInstrument.jLabel31.text")); 
        jPanel141.add(jLabel31);

        jTextField3.setText(bundle.getString("CrInstrument.jTextField3.text")); 
        jTextField3.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel141.add(jTextField3);

        jLabel46.setFont(jLabel46.getFont());
        jLabel46.setText(bundle.getString("CrInstrument.jLabel46.text")); 
        jPanel141.add(jLabel46);

        jTextField5.setText(bundle.getString("CrInstrument.jTextField5.text")); 
        jTextField5.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel141.add(jTextField5);

        jLabel47.setText(bundle.getString("CrInstrument.jLabel47.text")); 
        jPanel141.add(jLabel47);

        jTextField9.setText(bundle.getString("CrInstrument.jTextField9.text")); 
        jTextField9.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel141.add(jTextField9);

        jLabel54.setFont(jLabel54.getFont());
        jLabel54.setText(bundle.getString("CrInstrument.jLabel54.text")); 
        jPanel141.add(jLabel54);

        jTextField10.setText(bundle.getString("CrInstrument.jTextField10.text")); 
        jTextField10.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel141.add(jTextField10);

        jLabel55.setFont(jLabel55.getFont());
        jLabel55.setText(bundle.getString("CrInstrument.jLabel55.text")); 
        jPanel141.add(jLabel55);

        jTextField61.setText(bundle.getString("CrInstrument.jTextField61.text")); 
        jTextField61.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel141.add(jTextField61);

        jLabel56.setText(bundle.getString("CrInstrument.jLabel56.text")); 
        jPanel141.add(jLabel56);

        jPanel144.add(jPanel141);
        jPanel141.setBounds(20, 20, 910, 40);

        jPanel143.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel57.setFont(jLabel57.getFont());
        jLabel57.setText(bundle.getString("CrInstrument.jLabel57.text")); 
        jPanel143.add(jLabel57);

        jComboBox37.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel143.add(jComboBox37);

        jLabel72.setFont(jLabel72.getFont());
        jLabel72.setText(bundle.getString("CrInstrument.jLabel72.text")); 
        jPanel143.add(jLabel72);

        jComboBox53.setEditable(true);
        jComboBox53.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel143.add(jComboBox53);

        jLabel73.setFont(jLabel73.getFont());
        jLabel73.setText(bundle.getString("CrInstrument.jLabel73.text")); 
        jPanel143.add(jLabel73);

        jTextField73.setText(bundle.getString("CrInstrument.jTextField73.text")); 
        jTextField73.setPreferredSize(new java.awt.Dimension(56, 21));
        jPanel143.add(jTextField73);

        jLabel311.setFont(jLabel311.getFont());
        jLabel311.setText(bundle.getString("CrInstrument.jLabel311.text")); 
        jPanel143.add(jLabel311);

        jComboBox55.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel143.add(jComboBox55);

        jCheckBox3.setFont(jCheckBox3.getFont());
        jCheckBox3.setText(bundle.getString("CrInstrument.jCheckBox3.text")); 
        jPanel143.add(jCheckBox3);

        jCheckBox46.setFont(jCheckBox46.getFont());
        jCheckBox46.setText(bundle.getString("CrInstrument.jCheckBox46.text")); 
        jPanel143.add(jCheckBox46);

        jPanel144.add(jPanel143);
        jPanel143.setBounds(90, 70, 820, 40);

        jPanel36.add(jPanel144);
        jPanel144.setBounds(30, 430, 940, 120);

        jPanel145.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)), bundle.getString("CrInstrument.jPanel145.border.title"))); 
        jPanel145.setFont(jPanel145.getFont());
        jPanel145.setLayout(null);

        jPanel110.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel147.setFont(jLabel147.getFont());
        jLabel147.setText(bundle.getString("CrInstrument.jLabel147.text")); 
        jPanel110.add(jLabel147);

        jTextField45.setText(bundle.getString("CrInstrument.jTextField45.text")); 
        jTextField45.setPreferredSize(new java.awt.Dimension(306, 21));
        jPanel110.add(jTextField45);

        jLabel75.setFont(jLabel75.getFont());
        jLabel75.setText(bundle.getString("CrInstrument.jLabel75.text")); 
        jPanel110.add(jLabel75);

        jComboBox54.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel110.add(jComboBox54);

        jPanel145.add(jPanel110);
        jPanel110.setBounds(20, 20, 720, 30);

        jPanel146.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox38.setFont(jCheckBox38.getFont());
        jCheckBox38.setText(bundle.getString("CrInstrument.jCheckBox38.text")); 
        jPanel146.add(jCheckBox38);

        jCheckBox44.setFont(jCheckBox44.getFont());
        jCheckBox44.setText(bundle.getString("CrInstrument.jCheckBox44.text")); 
        jPanel146.add(jCheckBox44);

        jCheckBox45.setFont(jCheckBox45.getFont());
        jCheckBox45.setText(bundle.getString("CrInstrument.jCheckBox45.text")); 
        jPanel146.add(jCheckBox45);

        jPanel145.add(jPanel146);
        jPanel146.setBounds(20, 60, 530, 30);

        jPanel147.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel97.setFont(jLabel97.getFont());
        jLabel97.setText(bundle.getString("CrInstrument.jLabel97.text")); 
        jPanel147.add(jLabel97);

        jCheckBox43.setFont(jCheckBox43.getFont());
        jCheckBox43.setText(bundle.getString("CrInstrument.jCheckBox43.text")); 
        jPanel147.add(jCheckBox43);

        jLabel105.setText(bundle.getString("CrInstrument.jLabel105.text")); 
        jPanel147.add(jLabel105);

        jTextField74.setText(bundle.getString("CrInstrument.jTextField74.text")); 
        jTextField74.setPreferredSize(new java.awt.Dimension(56, 21));
        jPanel147.add(jTextField74);

        jLabel107.setText(bundle.getString("CrInstrument.jLabel107.text")); 
        jPanel147.add(jLabel107);

        jLabel108.setText(bundle.getString("CrInstrument.jLabel108.text")); 
        jPanel147.add(jLabel108);

        jTextField75.setText(bundle.getString("CrInstrument.jTextField75.text")); 
        jTextField75.setPreferredSize(new java.awt.Dimension(56, 21));
        jPanel147.add(jTextField75);

        jLabel139.setText(bundle.getString("CrInstrument.jLabel139.text")); 
        jPanel147.add(jLabel139);

        jLabel140.setFont(jLabel140.getFont());
        jLabel140.setText(bundle.getString("CrInstrument.jLabel140.text")); 
        jPanel147.add(jLabel140);

        jTextField76.setText(bundle.getString("CrInstrument.jTextField76.text")); 
        jTextField76.setPreferredSize(new java.awt.Dimension(56, 21));
        jPanel147.add(jTextField76);

        jLabel156.setText(bundle.getString("CrInstrument.jLabel156.text")); 
        jPanel147.add(jLabel156);

        jLabel141.setFont(jLabel141.getFont());
        jLabel141.setText(bundle.getString("CrInstrument.jLabel141.text")); 
        jPanel147.add(jLabel141);

        jTextField77.setText(bundle.getString("CrInstrument.jTextField77.text")); 
        jTextField77.setPreferredSize(new java.awt.Dimension(56, 21));
        jPanel147.add(jTextField77);

        jLabel154.setText(bundle.getString("CrInstrument.jLabel154.text")); 
        jPanel147.add(jLabel154);

        jPanel145.add(jPanel147);
        jPanel147.setBounds(20, 100, 690, 30);

        jPanel36.add(jPanel145);
        jPanel145.setBounds(30, 280, 940, 140);

        jTabbedPane4.addTab(bundle.getString("CrInstrument.jPanel36.TabConstraints.tabTitle"), jPanel36); 

        jPanel39.setFont(jPanel39.getFont());

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1009, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab(bundle.getString("CrInstrument.jPanel39.TabConstraints.tabTitle"), jPanel39); 

        jPanel41.setFont(jPanel41.getFont());

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1009, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab(bundle.getString("CrInstrument.jPanel41.TabConstraints.tabTitle"), jPanel41); 

        jPanel40.setFont(jPanel40.getFont());
        jPanel40.setLayout(null);

        jPanel123.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel137.setFont(jLabel137.getFont());
        jLabel137.setText(bundle.getString("CrInstrument.jLabel137.text")); 
        jPanel123.add(jLabel137);

        buttonGroup7.add(byStation);
        byStation.setFont(byStation.getFont());
        byStation.setText(bundle.getString("CrInstrument.byStation.text")); 
        byStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byStationActionPerformed(evt);
            }
        });
        jPanel123.add(byStation);

        buttonGroup7.add(byDevice);
        byDevice.setFont(byDevice.getFont());
        byDevice.setText(bundle.getString("CrInstrument.byDevice.text")); 
        byDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDeviceActionPerformed(evt);
            }
        });
        jPanel123.add(byDevice);

        buttonGroup7.add(byModel);
        byModel.setFont(byModel.getFont());
        byModel.setText(bundle.getString("CrInstrument.byModel.text")); 
        byModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byModelActionPerformed(evt);
            }
        });
        jPanel123.add(byModel);

        buttonGroup7.add(byDataName);
        byDataName.setFont(byDataName.getFont());
        byDataName.setSelected(true);
        byDataName.setText(bundle.getString("CrInstrument.byDataName.text")); 
        jPanel123.add(byDataName);

        buttonGroup7.add(byChartGroup);
        byChartGroup.setFont(byChartGroup.getFont());
        byChartGroup.setText(bundle.getString("CrInstrument.byChartGroup.text")); 
        byChartGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byChartGroupActionPerformed(evt);
            }
        });
        jPanel123.add(byChartGroup);

        chartGroupCB.setEditable(true);
        chartGroupCB.setFont(chartGroupCB.getFont());
        chartGroupCB.setPreferredSize(new java.awt.Dimension(129, 21));
        chartGroupCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chartGroupCBItemStateChanged(evt);
            }
        });
        jPanel123.add(chartGroupCB);

        jPanel40.add(jPanel123);
        jPanel123.setBounds(20, 60, 850, 40);

        jTabbedPane4.addTab(bundle.getString("CrInstrument.jPanel40.TabConstraints.tabTitle"), jPanel40); 

        UIPanel.add(jTabbedPane4, java.awt.BorderLayout.CENTER);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.UIPanel.TabConstraints.tabTitle"), UIPanel); 

        jPanel109.setLayout(new java.awt.BorderLayout());

        jPanel113.setLayout(new java.awt.BorderLayout());

        jPanel115.setBackground(new java.awt.Color(204, 255, 255));
        jPanel115.setFont(jPanel115.getFont());
        jPanel115.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel150.setFont(jLabel150.getFont().deriveFont(jLabel150.getFont().getStyle() | java.awt.Font.BOLD, jLabel150.getFont().getSize()+2));
        jLabel150.setText(bundle.getString("CrInstrument.jLabel150.text")); 
        jPanel115.add(jLabel150);

        showCB.setFont(showCB.getFont());
        showCB.setText(bundle.getString("CrInstrument.showCB.text")); 
        showCB.setOpaque(false);
        jPanel115.add(showCB);

        buttonGroup8.add(show16RB);
        show16RB.setFont(show16RB.getFont());
        show16RB.setText(bundle.getString("CrInstrument.show16RB.text")); 
        show16RB.setOpaque(false);
        jPanel115.add(show16RB);

        buttonGroup8.add(showStrRB);
        showStrRB.setFont(showStrRB.getFont());
        showStrRB.setText(bundle.getString("CrInstrument.showStrRB.text")); 
        showStrRB.setOpaque(false);
        jPanel115.add(showStrRB);

        crnlCB.setFont(crnlCB.getFont());
        crnlCB.setText(bundle.getString("CrInstrument.crnlCB.text")); 
        crnlCB.setOpaque(false);
        jPanel115.add(crnlCB);

        showTimeCB.setFont(showTimeCB.getFont());
        showTimeCB.setText(bundle.getString("CrInstrument.showTimeCB.text")); 
        showTimeCB.setOpaque(false);
        jPanel115.add(showTimeCB);

        showSrcCB.setFont(showSrcCB.getFont());
        showSrcCB.setText(bundle.getString("CrInstrument.showSrcCB.text")); 
        showSrcCB.setOpaque(false);
        jPanel115.add(showSrcCB);

        showSysMsgCB.setFont(showSysMsgCB.getFont());
        showSysMsgCB.setText(bundle.getString("CrInstrument.showSysMsgCB.text")); 
        showSysMsgCB.setOpaque(false);
        jPanel115.add(showSysMsgCB);

        saveFileCB.setFont(saveFileCB.getFont());
        saveFileCB.setText(bundle.getString("CrInstrument.saveFileCB.text")); 
        saveFileCB.setOpaque(false);
        jPanel115.add(saveFileCB);

        clearShowBtn.setFont(clearShowBtn.getFont());
        clearShowBtn.setText(bundle.getString("CrInstrument.clearShowBtn.text")); 
        clearShowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearShowBtnActionPerformed(evt);
            }
        });
        jPanel115.add(clearShowBtn);

        jPanel113.add(jPanel115, java.awt.BorderLayout.NORTH);

        jPanel116.setLayout(new java.awt.BorderLayout());

        jScrollPane11.setViewportView(receiveTP);

        jPanel116.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        receiveList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receiveListMouseClicked(evt);
            }
        });
        receiveList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                receiveListKeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(receiveList);

        jPanel116.add(jScrollPane12, java.awt.BorderLayout.WEST);

        jPanel113.add(jPanel116, java.awt.BorderLayout.CENTER);

        jPanel109.add(jPanel113, java.awt.BorderLayout.CENTER);

        jPanel114.setLayout(new java.awt.BorderLayout());

        jPanel117.setLayout(new java.awt.BorderLayout());

        sendTA.setColumns(20);
        sendTA.setRows(5);
        jScrollPane13.setViewportView(sendTA);

        jPanel117.add(jScrollPane13, java.awt.BorderLayout.CENTER);

        jScrollPane14.setViewportView(sendList);

        jPanel117.add(jScrollPane14, java.awt.BorderLayout.WEST);

        jPanel114.add(jPanel117, java.awt.BorderLayout.CENTER);

        jPanel118.setLayout(new java.awt.GridLayout(2, 0));

        jPanel119.setBackground(new java.awt.Color(0, 0, 204));
        jPanel119.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel151.setFont(jLabel151.getFont().deriveFont(jLabel151.getFont().getStyle() | java.awt.Font.BOLD, jLabel151.getFont().getSize()+2));
        jLabel151.setForeground(new java.awt.Color(255, 255, 255));
        jLabel151.setText(bundle.getString("CrInstrument.jLabel151.text")); 
        jPanel119.add(jLabel151);

        buttonGroup9.add(send16RB);
        send16RB.setFont(send16RB.getFont());
        send16RB.setForeground(new java.awt.Color(255, 255, 255));
        send16RB.setText(bundle.getString("CrInstrument.send16RB.text")); 
        send16RB.setOpaque(false);
        jPanel119.add(send16RB);

        buttonGroup9.add(sendStrRB);
        sendStrRB.setFont(sendStrRB.getFont());
        sendStrRB.setForeground(new java.awt.Color(255, 255, 255));
        sendStrRB.setText(bundle.getString("CrInstrument.sendStrRB.text")); 
        sendStrRB.setOpaque(false);
        jPanel119.add(sendStrRB);

        chkSumCB.setFont(chkSumCB.getFont());
        chkSumCB.setForeground(new java.awt.Color(255, 255, 255));
        chkSumCB.setText(bundle.getString("CrInstrument.chkSumCB.text")); 
        chkSumCB.setOpaque(false);
        jPanel119.add(chkSumCB);

        chkSumCBB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CheckSum", "Modbus CRC", "0x0D" }));
        jPanel119.add(chkSumCBB);

        jPanel118.add(jPanel119);

        jPanel120.setBackground(new java.awt.Color(0, 0, 204));
        jPanel120.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        continueSendCB.setFont(continueSendCB.getFont());
        continueSendCB.setForeground(new java.awt.Color(255, 255, 255));
        continueSendCB.setText(bundle.getString("CrInstrument.continueSendCB.text")); 
        continueSendCB.setOpaque(false);
        jPanel120.add(continueSendCB);

        jLabel152.setFont(jLabel152.getFont());
        jLabel152.setForeground(new java.awt.Color(255, 255, 255));
        jLabel152.setText(bundle.getString("CrInstrument.jLabel152.text")); 
        jPanel120.add(jLabel152);

        sendIntervalTF.setText(bundle.getString("CrInstrument.sendIntervalTF.text")); 
        sendIntervalTF.setPreferredSize(new java.awt.Dimension(46, 21));
        jPanel120.add(sendIntervalTF);

        jLabel153.setFont(jLabel153.getFont());
        jLabel153.setForeground(new java.awt.Color(255, 255, 255));
        jLabel153.setText(bundle.getString("CrInstrument.jLabel153.text")); 
        jPanel120.add(jLabel153);

        sendBtn.setFont(sendBtn.getFont());
        sendBtn.setText(bundle.getString("CrInstrument.sendBtn.text")); 
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });
        jPanel120.add(sendBtn);

        stopContinueSendBtn.setFont(stopContinueSendBtn.getFont());
        stopContinueSendBtn.setText(bundle.getString("CrInstrument.stopContinueSendBtn.text")); 
        stopContinueSendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopContinueSendBtnActionPerformed(evt);
            }
        });
        jPanel120.add(stopContinueSendBtn);

        clearSendBtn.setFont(clearSendBtn.getFont());
        clearSendBtn.setText(bundle.getString("CrInstrument.clearSendBtn.text")); 
        clearSendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSendBtnActionPerformed(evt);
            }
        });
        jPanel120.add(clearSendBtn);

        jPanel118.add(jPanel120);

        jPanel114.add(jPanel118, java.awt.BorderLayout.PAGE_START);

        jPanel109.add(jPanel114, java.awt.BorderLayout.SOUTH);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.jPanel109.TabConstraints.tabTitle"), jPanel109); 

        NodeMgntPanel.setFont(NodeMgntPanel.getFont());
        NodeMgntPanel.setLayout(null);

        jButton41.setFont(jButton41.getFont().deriveFont(jButton41.getFont().getSize()+12f));
        jButton41.setText(bundle.getString("CrInstrument.jButton41.text")); 
        NodeMgntPanel.add(jButton41);
        jButton41.setBounds(780, 70, 200, 60);

        jScrollPane15.setViewportView(jList9);

        NodeMgntPanel.add(jScrollPane15);
        jScrollPane15.setBounds(40, 40, 80, 190);

        jScrollPane16.setViewportView(jList10);

        NodeMgntPanel.add(jScrollPane16);
        jScrollPane16.setBounds(70, 380, 70, 160);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.NodeMgntPanel.TabConstraints.tabTitle"), NodeMgntPanel); 

        jPanel49.setFont(jPanel49.getFont());
        jPanel49.setLayout(null);

        jPanel71.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel71.border.title"))); 
        jPanel71.setFont(jPanel71.getFont());
        jPanel71.setLayout(null);

        eventList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(eventList);

        jPanel71.add(jScrollPane6);
        jScrollPane6.setBounds(10, 20, 70, 400);

        jButton33.setFont(jButton33.getFont());
        jButton33.setText(bundle.getString("CrInstrument.jButton33.text")); 
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel71.add(jButton33);
        jButton33.setBounds(10, 490, 110, 23);

        jButton34.setFont(jButton34.getFont());
        jButton34.setText(bundle.getString("CrInstrument.jButton34.text")); 
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel71.add(jButton34);
        jButton34.setBounds(10, 520, 110, 23);

        jButton9.setFont(jButton9.getFont());
        jButton9.setText(bundle.getString("CrInstrument.jButton9.text")); 
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel71.add(jButton9);
        jButton9.setBounds(10, 430, 110, 23);

        jButton12.setFont(jButton12.getFont());
        jButton12.setText(bundle.getString("CrInstrument.jButton12.text")); 
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel71.add(jButton12);
        jButton12.setBounds(10, 460, 110, 23);

        jLabel87.setFont(jLabel87.getFont());
        jLabel87.setText(bundle.getString("CrInstrument.jLabel87.text")); 
        jPanel71.add(jLabel87);
        jLabel87.setBounds(91, 40, 90, 20);

        jLabel88.setFont(jLabel88.getFont());
        jLabel88.setText(bundle.getString("CrInstrument.jLabel88.text")); 
        jPanel71.add(jLabel88);
        jLabel88.setBounds(100, 240, 80, 20);

        conditionList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionList2MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(conditionList2);

        jPanel71.add(jScrollPane17);
        jScrollPane17.setBounds(110, 60, 60, 110);

        jScrollPane18.setViewportView(actionList2);

        jPanel71.add(jScrollPane18);
        jScrollPane18.setBounds(110, 260, 60, 90);

        jButton28.setFont(jButton28.getFont());
        jButton28.setText(bundle.getString("CrInstrument.jButton28.text")); 
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel71.add(jButton28);
        jButton28.setBounds(89, 180, 90, 23);

        jButton52.setFont(jButton52.getFont());
        jButton52.setText(bundle.getString("CrInstrument.jButton52.text")); 
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jPanel71.add(jButton52);
        jButton52.setBounds(89, 360, 90, 23);

        jPanel49.add(jPanel71);
        jPanel71.setBounds(20, 30, 190, 550);

        jPanel70.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel70.border.title"))); 
        jPanel70.setFont(jPanel70.getFont());
        jPanel70.setLayout(null);

        actionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionListMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(actionList);

        jPanel70.add(jScrollPane8);
        jScrollPane8.setBounds(10, 60, 70, 130);

        jButton25.setFont(jButton25.getFont());
        jButton25.setText(bundle.getString("CrInstrument.jButton25.text")); 
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton25);
        jButton25.setBounds(220, 240, 130, 23);

        jButton18.setFont(jButton18.getFont());
        jButton18.setText(bundle.getString("CrInstrument.jButton18.text")); 
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton18);
        jButton18.setBounds(220, 210, 130, 23);

        updateActionBtn.setFont(updateActionBtn.getFont());
        updateActionBtn.setText(bundle.getString("CrInstrument.updateActionBtn.text")); 
        updateActionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionBtnActionPerformed(evt);
            }
        });
        jPanel70.add(updateActionBtn);
        updateActionBtn.setBounds(120, 240, 100, 23);

        jPanel76.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel86.setFont(jLabel86.getFont());
        jLabel86.setText(bundle.getString("CrInstrument.jLabel86.text")); 
        jPanel76.add(jLabel86);

        jComboBox19.setEditable(true);
        jComboBox19.setFont(jComboBox19.getFont());
        jComboBox19.setPreferredSize(new java.awt.Dimension(200, 25));
        jComboBox19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox19ItemStateChanged(evt);
            }
        });
        jPanel76.add(jComboBox19);

        jPanel70.add(jPanel76);
        jPanel76.setBounds(90, 60, 270, 35);

        jPanel77.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel83.setFont(jLabel83.getFont());
        jLabel83.setText(bundle.getString("CrInstrument.jLabel83.text")); 
        jPanel77.add(jLabel83);

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Set device SN", "Set data value", "Send email message", "Send SMS message to cell phone", "Send command", "Stop continue send command", "Connect port", "Disconnect port", "Start monitor", "Stop monitor", "Open URL", "Exit application", "Restart application", "Java class" }));
        jComboBox18.setPreferredSize(new java.awt.Dimension(250, 25));
        jComboBox18.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox18ItemStateChanged(evt);
            }
        });
        jPanel77.add(jComboBox18);

        jPanel70.add(jPanel77);
        jPanel77.setBounds(10, 20, 330, 35);

        jPanel79.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), bundle.getString("CrInstrument.jPanel79.border.title"))); 
        jPanel79.setFont(jPanel79.getFont());
        jPanel79.setLayout(null);

        jPanel83.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel95.setFont(jLabel95.getFont());
        jLabel95.setText(bundle.getString("CrInstrument.jLabel95.text")); 
        jPanel83.add(jLabel95);

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Byte data", "String data" }));
        jComboBox28.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox28ItemStateChanged(evt);
            }
        });
        jPanel83.add(jComboBox28);

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Whole line", "Separated by space", "Separated by ',' ", "Fixed column length" }));
        jComboBox29.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox29ItemStateChanged(evt);
            }
        });
        jPanel83.add(jComboBox29);

        jLabel79.setFont(jLabel79.getFont());
        jLabel79.setText(bundle.getString("CrInstrument.jLabel79.text")); 
        jPanel83.add(jLabel79);

        jTextField56.setText(bundle.getString("CrInstrument.jTextField56.text")); 
        jTextField56.setPreferredSize(new java.awt.Dimension(26, 25));
        jPanel83.add(jTextField56);

        jPanel79.add(jPanel83);
        jPanel83.setBounds(10, 50, 410, 40);

        jPanel86.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox4.setFont(jCheckBox4.getFont());
        jCheckBox4.setText(bundle.getString("CrInstrument.jCheckBox4.text")); 
        jPanel86.add(jCheckBox4);

        jTextField27.setText(bundle.getString("CrInstrument.jTextField27.text")); 
        jTextField27.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel86.add(jTextField27);

        jLabel100.setText(bundle.getString("CrInstrument.jLabel100.text")); 
        jPanel86.add(jLabel100);

        jTextField28.setText(bundle.getString("CrInstrument.jTextField28.text")); 
        jTextField28.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel86.add(jTextField28);

        jLabel101.setText(bundle.getString("CrInstrument.jLabel101.text")); 
        jPanel86.add(jLabel101);

        jTextField29.setText(bundle.getString("CrInstrument.jTextField29.text")); 
        jTextField29.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel86.add(jTextField29);

        jPanel79.add(jPanel86);
        jPanel86.setBounds(30, 170, 390, 30);

        jPanel87.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox5.setFont(jCheckBox5.getFont());
        jCheckBox5.setText(bundle.getString("CrInstrument.jCheckBox5.text")); 
        jPanel87.add(jCheckBox5);

        jTextField30.setText(bundle.getString("CrInstrument.jTextField30.text")); 
        jTextField30.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel87.add(jTextField30);

        jLabel102.setFont(jLabel102.getFont());
        jLabel102.setText(bundle.getString("CrInstrument.jLabel102.text")); 
        jPanel87.add(jLabel102);

        jPanel79.add(jPanel87);
        jPanel87.setBounds(50, 200, 360, 35);

        jPanel88.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox7.setFont(jCheckBox7.getFont());
        jCheckBox7.setText(bundle.getString("CrInstrument.jCheckBox7.text")); 
        jPanel88.add(jCheckBox7);

        jCheckBox6.setFont(jCheckBox6.getFont());
        jCheckBox6.setText(bundle.getString("CrInstrument.jCheckBox6.text")); 
        jPanel88.add(jCheckBox6);

        jPanel79.add(jPanel88);
        jPanel88.setBounds(50, 230, 340, 30);

        jPanel104.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel149.setFont(jLabel149.getFont());
        jLabel149.setText(bundle.getString("CrInstrument.jLabel149.text")); 
        jPanel104.add(jLabel149);

        jLabel39.setText(bundle.getString("CrInstrument.jLabel39.text")); 
        jPanel104.add(jLabel39);

        jPanel79.add(jPanel104);
        jPanel104.setBounds(200, 20, 190, 30);

        jPanel75.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel148.setText(bundle.getString("CrInstrument.jLabel148.text")); 
        jPanel75.add(jLabel148);

        jLabel96.setFont(jLabel96.getFont());
        jLabel96.setText(bundle.getString("CrInstrument.jLabel96.text")); 
        jPanel75.add(jLabel96);

        jTextField15.setText(bundle.getString("CrInstrument.jTextField15.text")); 
        jTextField15.setToolTipText(bundle.getString("CrInstrument.jTextField15.toolTipText")); 
        jTextField15.setPreferredSize(new java.awt.Dimension(25, 25));
        jPanel75.add(jTextField15);

        jCheckBox34.setFont(jCheckBox34.getFont());
        jCheckBox34.setText(bundle.getString("CrInstrument.jCheckBox34.text")); 
        jPanel75.add(jCheckBox34);

        jTextField16.setText(bundle.getString("CrInstrument.jTextField16.text")); 
        jTextField16.setToolTipText(bundle.getString("CrInstrument.jTextField16.toolTipText")); 
        jTextField16.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel75.add(jTextField16);

        jLabel98.setText(bundle.getString("CrInstrument.jLabel98.text")); 
        jPanel75.add(jLabel98);

        jTextField21.setText(bundle.getString("CrInstrument.jTextField21.text")); 
        jTextField21.setToolTipText(bundle.getString("CrInstrument.jTextField21.toolTipText")); 
        jTextField21.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel75.add(jTextField21);

        jCheckBox27.setText(bundle.getString("CrInstrument.jCheckBox27.text")); 
        jPanel75.add(jCheckBox27);

        jPanel79.add(jPanel75);
        jPanel75.setBounds(10, 100, 410, 30);

        jPanel128.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel99.setFont(jLabel99.getFont());
        jLabel99.setText(bundle.getString("CrInstrument.jLabel99.text")); 
        jPanel128.add(jLabel99);

        jCheckBox28.setFont(jCheckBox28.getFont());
        jCheckBox28.setText(bundle.getString("CrInstrument.jCheckBox28.text")); 
        jPanel128.add(jCheckBox28);

        jTextField63.setText(bundle.getString("CrInstrument.jTextField63.text")); 
        jTextField63.setPreferredSize(new java.awt.Dimension(126, 25));
        jPanel128.add(jTextField63);

        jPanel79.add(jPanel128);
        jPanel128.setBounds(10, 140, 400, 30);

        jPanel70.add(jPanel79);
        jPanel79.setBounds(360, 20, 430, 270);

        jButton31.setFont(jButton31.getFont());
        jButton31.setText(bundle.getString("CrInstrument.jButton31.text")); 
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton31);
        jButton31.setBounds(10, 210, 110, 23);

        jButton32.setFont(jButton32.getFont());
        jButton32.setText(bundle.getString("CrInstrument.jButton32.text")); 
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton32);
        jButton32.setBounds(10, 240, 110, 23);

        jButton50.setFont(jButton50.getFont());
        jButton50.setText(bundle.getString("CrInstrument.jButton50.text")); 
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton50);
        jButton50.setBounds(120, 210, 100, 23);

        jPanel78.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel78.border.title"))); 
        jPanel78.setFont(jPanel78.getFont());
        jPanel78.setLayout(null);

        buttonGroup6.add(jRadioButton10);
        jRadioButton10.setFont(jRadioButton10.getFont());
        jRadioButton10.setText(bundle.getString("CrInstrument.jRadioButton10.text")); 
        jPanel78.add(jRadioButton10);
        jRadioButton10.setBounds(20, 190, 350, 23);

        buttonGroup6.add(jRadioButton11);
        jRadioButton11.setFont(jRadioButton11.getFont());
        jRadioButton11.setText(bundle.getString("CrInstrument.jRadioButton11.text")); 
        jPanel78.add(jRadioButton11);
        jRadioButton11.setBounds(20, 220, 350, 23);

        jPanel94.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel110.setFont(jLabel110.getFont());
        jLabel110.setText(bundle.getString("CrInstrument.jLabel110.text")); 
        jPanel94.add(jLabel110);

        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Byte data", "String data" }));
        jPanel94.add(jComboBox32);

        jComboBox41.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Whole line", "Separated by space", "Separated by ',' ", "Fixed column length" }));
        jPanel94.add(jComboBox41);

        jLabel111.setFont(jLabel111.getFont());
        jLabel111.setText(bundle.getString("CrInstrument.jLabel111.text")); 
        jPanel94.add(jLabel111);

        jTextField57.setText(bundle.getString("CrInstrument.jTextField57.text")); 
        jTextField57.setPreferredSize(new java.awt.Dimension(26, 25));
        jPanel94.add(jTextField57);

        jPanel78.add(jPanel94);
        jPanel94.setBounds(10, 30, 380, 30);

        jPanel121.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox33.setFont(jCheckBox33.getFont());
        jCheckBox33.setText(bundle.getString("CrInstrument.jCheckBox33.text")); 
        jPanel121.add(jCheckBox33);

        jLabel112.setFont(jLabel112.getFont());
        jLabel112.setText(bundle.getString("CrInstrument.jLabel112.text")); 
        jPanel121.add(jLabel112);

        jTextField58.setText(bundle.getString("CrInstrument.jTextField58.text")); 
        jTextField58.setToolTipText(bundle.getString("CrInstrument.jTextField58.toolTipText")); 
        jTextField58.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel121.add(jTextField58);

        jCheckBox36.setFont(jCheckBox36.getFont());
        jCheckBox36.setText(bundle.getString("CrInstrument.jCheckBox36.text")); 
        jPanel121.add(jCheckBox36);

        jTextField59.setText(bundle.getString("CrInstrument.jTextField59.text")); 
        jTextField59.setToolTipText(bundle.getString("CrInstrument.jTextField59.toolTipText")); 
        jTextField59.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel121.add(jTextField59);

        jLabel113.setFont(jLabel113.getFont());
        jLabel113.setText(bundle.getString("CrInstrument.jLabel113.text")); 
        jPanel121.add(jLabel113);

        jTextField60.setText(bundle.getString("CrInstrument.jTextField60.text")); 
        jTextField60.setToolTipText(bundle.getString("CrInstrument.jTextField60.toolTipText")); 
        jTextField60.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel121.add(jTextField60);

        jPanel78.add(jPanel121);
        jPanel121.setBounds(10, 90, 380, 30);

        jPanel137.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox39.setFont(jCheckBox39.getFont());
        jCheckBox39.setText(bundle.getString("CrInstrument.jCheckBox39.text")); 
        jPanel137.add(jCheckBox39);

        jLabel164.setFont(jLabel164.getFont());
        jLabel164.setText(bundle.getString("CrInstrument.jLabel164.text")); 
        jPanel137.add(jLabel164);

        jTextField67.setText(bundle.getString("CrInstrument.jTextField67.text")); 
        jTextField67.setToolTipText(bundle.getString("CrInstrument.jTextField67.toolTipText")); 
        jTextField67.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel137.add(jTextField67);

        jCheckBox40.setFont(jCheckBox40.getFont());
        jCheckBox40.setText(bundle.getString("CrInstrument.jCheckBox40.text")); 
        jPanel137.add(jCheckBox40);

        jTextField68.setText(bundle.getString("CrInstrument.jTextField68.text")); 
        jTextField68.setToolTipText(bundle.getString("CrInstrument.jTextField68.toolTipText")); 
        jTextField68.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel137.add(jTextField68);

        jLabel165.setFont(jLabel165.getFont());
        jLabel165.setText(bundle.getString("CrInstrument.jLabel165.text")); 
        jPanel137.add(jLabel165);

        jTextField69.setText(bundle.getString("CrInstrument.jTextField69.text")); 
        jTextField69.setToolTipText(bundle.getString("CrInstrument.jTextField69.toolTipText")); 
        jTextField69.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel137.add(jTextField69);

        jPanel78.add(jPanel137);
        jPanel137.setBounds(10, 150, 380, 35);

        jPanel138.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox41.setFont(jCheckBox41.getFont());
        jCheckBox41.setText(bundle.getString("CrInstrument.jCheckBox41.text")); 
        jPanel138.add(jCheckBox41);

        jLabel166.setFont(jLabel166.getFont());
        jLabel166.setText(bundle.getString("CrInstrument.jLabel166.text")); 
        jPanel138.add(jLabel166);

        jTextField70.setText(bundle.getString("CrInstrument.jTextField70.text")); 
        jTextField70.setToolTipText(bundle.getString("CrInstrument.jTextField70.toolTipText")); 
        jTextField70.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel138.add(jTextField70);

        jCheckBox42.setFont(jCheckBox42.getFont());
        jCheckBox42.setText(bundle.getString("CrInstrument.jCheckBox42.text")); 
        jPanel138.add(jCheckBox42);

        jTextField71.setText(bundle.getString("CrInstrument.jTextField71.text")); 
        jTextField71.setToolTipText(bundle.getString("CrInstrument.jTextField71.toolTipText")); 
        jTextField71.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel138.add(jTextField71);

        jLabel167.setFont(jLabel167.getFont());
        jLabel167.setText(bundle.getString("CrInstrument.jLabel167.text")); 
        jPanel138.add(jLabel167);

        jTextField72.setText(bundle.getString("CrInstrument.jTextField72.text")); 
        jTextField72.setToolTipText(bundle.getString("CrInstrument.jTextField72.toolTipText")); 
        jTextField72.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel138.add(jTextField72);

        jPanel78.add(jPanel138);
        jPanel138.setBounds(10, 120, 380, 30);

        jPanel84.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel155.setFont(jLabel155.getFont());
        jLabel155.setText(bundle.getString("CrInstrument.jLabel155.text")); 
        jPanel84.add(jLabel155);

        jTextField53.setText(bundle.getString("CrInstrument.jTextField53.text")); 
        jTextField53.setToolTipText(bundle.getString("CrInstrument.jTextField53.toolTipText")); 
        jTextField53.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel84.add(jTextField53);

        jPanel78.add(jPanel84);
        jPanel84.setBounds(30, 60, 330, 30);

        jPanel70.add(jPanel78);
        jPanel78.setBounds(390, 30, 400, 260);

        jPanel122.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel142.setFont(jLabel142.getFont());
        jLabel142.setText(bundle.getString("CrInstrument.jLabel142.text")); 
        jPanel122.add(jLabel142);

        jComboBox44.setEditable(true);
        jComboBox44.setFont(jComboBox44.getFont());
        jComboBox44.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox44.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox44ItemStateChanged(evt);
            }
        });
        jPanel122.add(jComboBox44);

        jPanel70.add(jPanel122);
        jPanel122.setBounds(90, 135, 260, 35);

        jPanel132.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel159.setFont(jLabel159.getFont());
        jLabel159.setText(bundle.getString("CrInstrument.jLabel159.text")); 
        jPanel132.add(jLabel159);

        jComboBox45.setEditable(true);
        jComboBox45.setFont(jComboBox45.getFont());
        jComboBox45.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox45.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox45ItemStateChanged(evt);
            }
        });
        jPanel132.add(jComboBox45);

        jPanel70.add(jPanel132);
        jPanel132.setBounds(90, 170, 270, 35);

        jPanel136.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel163.setFont(jLabel163.getFont());
        jLabel163.setText(bundle.getString("CrInstrument.jLabel163.text")); 
        jPanel136.add(jLabel163);

        jComboBox49.setEditable(true);
        jComboBox49.setFont(jComboBox49.getFont());
        jComboBox49.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox49.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox49ItemStateChanged(evt);
            }
        });
        jPanel136.add(jComboBox49);

        jPanel70.add(jPanel136);
        jPanel136.setBounds(90, 100, 260, 35);

        jPanel85.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel85.border.title"))); 
        jPanel85.setFont(jPanel85.getFont());
        jPanel85.setLayout(null);

        jPanel89.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Byte data", "String data" }));
        jPanel89.add(jComboBox30);

        jTextField31.setText(bundle.getString("CrInstrument.jTextField31.text")); 
        jTextField31.setPreferredSize(new java.awt.Dimension(260, 25));
        jPanel89.add(jTextField31);

        jPanel85.add(jPanel89);
        jPanel89.setBounds(10, 30, 380, 40);

        jPanel90.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox8.setText(bundle.getString("CrInstrument.jCheckBox8.text")); 
        jPanel90.add(jCheckBox8);

        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Checksum", "Modbus CRC", "0x0D" }));
        jPanel90.add(jComboBox31);

        jPanel85.add(jPanel90);
        jPanel90.setBounds(10, 110, 360, 33);

        jPanel91.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox11.setText(bundle.getString("CrInstrument.jCheckBox11.text")); 
        jCheckBox11.setActionCommand(bundle.getString("CrInstrument.jCheckBox11.actionCommand")); 
        jPanel91.add(jCheckBox11);

        jTextField32.setText(bundle.getString("CrInstrument.jTextField32.text")); 
        jTextField32.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel91.add(jTextField32);

        jLabel104.setText(bundle.getString("CrInstrument.jLabel104.text")); 
        jPanel91.add(jLabel104);

        jPanel85.add(jPanel91);
        jPanel91.setBounds(10, 150, 370, 40);

        jPanel129.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox29.setText(bundle.getString("CrInstrument.jCheckBox29.text")); 
        jPanel129.add(jCheckBox29);

        jTextField64.setText(bundle.getString("CrInstrument.jTextField64.text")); 
        jTextField64.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel129.add(jTextField64);

        jPanel85.add(jPanel129);
        jPanel129.setBounds(90, 70, 290, 40);

        jPanel126.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)), bundle.getString("CrInstrument.jPanel126.border.title"))); 
        jPanel126.setFont(jPanel126.getFont());
        jPanel126.setLayout(null);

        jPanel127.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel158.setFont(jLabel158.getFont());
        jLabel158.setText(bundle.getString("CrInstrument.jLabel158.text")); 
        jPanel127.add(jLabel158);

        jTextField62.setText(bundle.getString("CrInstrument.jTextField62.text")); 
        jTextField62.setPreferredSize(new java.awt.Dimension(256, 25));
        jPanel127.add(jTextField62);

        jPanel126.add(jPanel127);
        jPanel127.setBounds(10, 20, 370, 40);

        jPanel85.add(jPanel126);
        jPanel126.setBounds(0, 90, 400, 80);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), bundle.getString("CrInstrument.jPanel26.border.title"))); 
        jPanel26.setLayout(null);

        jPanel139.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel22.setText(bundle.getString("CrInstrument.jLabel22.text")); 
        jPanel139.add(jLabel22);

        jTextField54.setText(bundle.getString("CrInstrument.jTextField54.text")); 
        jTextField54.setPreferredSize(new java.awt.Dimension(306, 25));
        jPanel139.add(jTextField54);

        jPanel26.add(jPanel139);
        jPanel139.setBounds(10, 30, 380, 40);

        jPanel85.add(jPanel26);
        jPanel26.setBounds(0, 30, 400, 90);

        jPanel70.add(jPanel85);
        jPanel85.setBounds(370, 40, 410, 250);

        jPanel49.add(jPanel70);
        jPanel70.setBounds(210, 280, 800, 300);

        jPanel69.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), bundle.getString("CrInstrument.jPanel69.border.title"))); 
        jPanel69.setFont(jPanel69.getFont());
        jPanel69.setLayout(null);

        conditionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionListMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(conditionList);

        jPanel69.add(jScrollPane7);
        jScrollPane7.setBounds(10, 70, 70, 90);

        jButton11.setFont(jButton11.getFont());
        jButton11.setText(bundle.getString("CrInstrument.jButton11.text")); 
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel69.add(jButton11);
        jButton11.setBounds(210, 180, 130, 23);

        jButton13.setFont(jButton13.getFont());
        jButton13.setText(bundle.getString("CrInstrument.jButton13.text")); 
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel69.add(jButton13);
        jButton13.setBounds(210, 210, 130, 23);

        jLabel81.setBackground(new java.awt.Color(255, 255, 255));
        jLabel81.setText(bundle.getString("CrInstrument.jLabel81.text")); 
        jLabel81.setOpaque(true);
        jPanel69.add(jLabel81);
        jLabel81.setBounds(230, 40, 100, 0);

        updateConditionBtn.setFont(updateConditionBtn.getFont());
        updateConditionBtn.setText(bundle.getString("CrInstrument.updateConditionBtn.text")); 
        updateConditionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateConditionBtnActionPerformed(evt);
            }
        });
        jPanel69.add(updateConditionBtn);
        updateConditionBtn.setBounds(110, 210, 100, 23);

        jPanel72.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), bundle.getString("CrInstrument.jPanel72.border.title"))); 
        jPanel72.setFont(jPanel72.getFont());
        jPanel72.setLayout(null);

        jPanel80.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel90.setFont(jLabel90.getFont());
        jLabel90.setText(bundle.getString("CrInstrument.jLabel90.text")); 
        jPanel80.add(jLabel90);

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Byte data", "String data" }));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });
        jPanel80.add(jComboBox20);

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Whole line", "Separated by space", "Separated by ',' ", "Fixed column length" }));
        jComboBox26.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox26ItemStateChanged(evt);
            }
        });
        jPanel80.add(jComboBox26);

        jLabel130.setFont(jLabel130.getFont());
        jLabel130.setText(bundle.getString("CrInstrument.jLabel130.text")); 
        jPanel80.add(jLabel130);

        jTextField46.setText(bundle.getString("CrInstrument.jTextField46.text")); 
        jTextField46.setPreferredSize(new java.awt.Dimension(26, 25));
        jPanel80.add(jTextField46);

        jPanel72.add(jPanel80);
        jPanel80.setBounds(10, 20, 410, 30);

        jPanel81.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel93.setFont(jLabel93.getFont());
        jLabel93.setText(bundle.getString("CrInstrument.jLabel93.text")); 
        jPanel81.add(jLabel93);

        jTextField13.setText(bundle.getString("CrInstrument.jTextField13.text")); 
        jTextField13.setToolTipText(bundle.getString("CrInstrument.jTextField13.toolTipText")); 
        jTextField13.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel81.add(jTextField13);

        jCheckBox35.setFont(jCheckBox35.getFont());
        jCheckBox35.setText(bundle.getString("CrInstrument.jCheckBox35.text")); 
        jPanel81.add(jCheckBox35);

        jTextField4.setText(bundle.getString("CrInstrument.jTextField4.text")); 
        jTextField4.setToolTipText(bundle.getString("CrInstrument.jTextField4.toolTipText")); 
        jTextField4.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel81.add(jTextField4);

        jLabel92.setFont(jLabel92.getFont());
        jLabel92.setText(bundle.getString("CrInstrument.jLabel92.text")); 
        jPanel81.add(jLabel92);

        jTextField12.setText(bundle.getString("CrInstrument.jTextField12.text")); 
        jTextField12.setToolTipText(bundle.getString("CrInstrument.jTextField12.toolTipText")); 
        jTextField12.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel81.add(jTextField12);

        jPanel72.add(jPanel81);
        jPanel81.setBounds(50, 50, 370, 30);

        jPanel82.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel94.setFont(jLabel94.getFont());
        jLabel94.setText(bundle.getString("CrInstrument.jLabel94.text")); 
        jPanel82.add(jLabel94);

        jCheckBox30.setFont(jCheckBox30.getFont());
        jCheckBox30.setText(bundle.getString("CrInstrument.jCheckBox30.text")); 
        jPanel82.add(jCheckBox30);

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ">", "=", "<", ">=", "<=", "!=" }));
        jPanel82.add(jComboBox27);

        jTextField14.setText(bundle.getString("CrInstrument.jTextField14.text")); 
        jTextField14.setPreferredSize(new java.awt.Dimension(100, 21));
        jPanel82.add(jTextField14);

        jPanel72.add(jPanel82);
        jPanel82.setBounds(10, 90, 380, 30);

        jPanel130.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox31.setFont(jCheckBox31.getFont());
        jCheckBox31.setText(bundle.getString("CrInstrument.jCheckBox31.text")); 
        jPanel130.add(jCheckBox31);

        jComboBox39.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ">", "=", "<", ">=", "<=", "!=" }));
        jPanel130.add(jComboBox39);

        jTextField65.setText(bundle.getString("CrInstrument.jTextField65.text")); 
        jTextField65.setPreferredSize(new java.awt.Dimension(100, 21));
        jPanel130.add(jTextField65);

        jPanel72.add(jPanel130);
        jPanel130.setBounds(70, 120, 320, 33);

        jPanel131.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox32.setFont(jCheckBox32.getFont());
        jCheckBox32.setText(bundle.getString("CrInstrument.jCheckBox32.text")); 
        jPanel131.add(jCheckBox32);

        jComboBox43.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ">", "=", "<", ">=", "<=", "!=" }));
        jPanel131.add(jComboBox43);

        jTextField66.setText(bundle.getString("CrInstrument.jTextField66.text")); 
        jTextField66.setPreferredSize(new java.awt.Dimension(100, 21));
        jPanel131.add(jTextField66);

        jPanel72.add(jPanel131);
        jPanel131.setBounds(70, 150, 320, 30);

        jPanel69.add(jPanel72);
        jPanel72.setBounds(360, 10, 430, 200);

        jPanel73.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel80.setFont(jLabel80.getFont());
        jLabel80.setText(bundle.getString("CrInstrument.jLabel80.text")); 
        jPanel73.add(jLabel80);

        jComboBox14.setEditable(true);
        jComboBox14.setFont(jComboBox14.getFont());
        jComboBox14.setPreferredSize(new java.awt.Dimension(200, 25));
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });
        jPanel73.add(jComboBox14);

        jPanel69.add(jPanel73);
        jPanel73.setBounds(90, 50, 270, 30);

        jPanel74.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel78.setFont(jLabel78.getFont());
        jLabel78.setText(bundle.getString("CrInstrument.jLabel78.text")); 
        jPanel74.add(jLabel78);

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "After system startup", "Before connected", "After connected", "Before start monitor", "After start monitor", "Before stop monitor", "After stop monitor", "Before disconnected", "After disconnected", "Over upper take-action level", "Under lower take-action level", "Over upper alert level", "Under lower alert level", "Data condition", "Data checked by Java class", "Any data", "Click button 01", "Click button 02", "Click button 03", "Click button 04", "Click button 05", "Click button 06", "Click button 07", "Click button 08", "Click button 09", "Click button 10", "Click file menuitem 01", "Click file menuitem 02", "Click file menuitem 03", "Click file menuitem 04", "Click file menuitem 05", "Click help menuitem 01", "Click help menuitem 02", "Click help menuitem 03", "Click help menuitem 04", "Click help menuitem 05", "Click tool menuitem 01", "Click tool menuitem 02", "Click tool menuitem 03", "Click tool menuitem 04", "Click tool menuitem 05", "Before system terminated", " ", " ", " " }));
        jComboBox16.setPreferredSize(new java.awt.Dimension(250, 21));
        jComboBox16.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox16ItemStateChanged(evt);
            }
        });
        jPanel74.add(jComboBox16);

        jPanel69.add(jPanel74);
        jPanel74.setBounds(10, 19, 350, 31);

        jButton29.setFont(jButton29.getFont());
        jButton29.setText(bundle.getString("CrInstrument.jButton29.text")); 
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel69.add(jButton29);
        jButton29.setBounds(10, 180, 100, 23);

        jButton30.setFont(jButton30.getFont());
        jButton30.setText(bundle.getString("CrInstrument.jButton30.text")); 
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel69.add(jButton30);
        jButton30.setBounds(10, 210, 100, 23);

        jButton51.setFont(jButton51.getFont());
        jButton51.setText(bundle.getString("CrInstrument.jButton51.text")); 
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        jPanel69.add(jButton51);
        jButton51.setBounds(110, 180, 100, 23);

        jPanel124.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), bundle.getString("CrInstrument.jPanel124.border.title"))); 
        jPanel124.setFont(jPanel124.getFont());
        jPanel124.setLayout(null);

        jPanel125.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel157.setFont(jLabel157.getFont());
        jLabel157.setText(bundle.getString("CrInstrument.jLabel157.text")); 
        jPanel125.add(jLabel157);

        jTextField55.setText(bundle.getString("CrInstrument.jTextField55.text")); 
        jTextField55.setPreferredSize(new java.awt.Dimension(256, 25));
        jPanel125.add(jTextField55);

        jPanel124.add(jPanel125);
        jPanel125.setBounds(12, 27, 350, 30);

        jPanel69.add(jPanel124);
        jPanel124.setBounds(390, 100, 390, 80);

        jPanel133.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel160.setFont(jLabel160.getFont());
        jLabel160.setText(bundle.getString("CrInstrument.jLabel160.text")); 
        jPanel133.add(jLabel160);

        jComboBox46.setEditable(true);
        jComboBox46.setFont(jComboBox46.getFont());
        jComboBox46.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox46.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox46ItemStateChanged(evt);
            }
        });
        jPanel133.add(jComboBox46);

        jPanel69.add(jPanel133);
        jPanel133.setBounds(90, 110, 270, 35);

        jPanel134.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel161.setFont(jLabel161.getFont());
        jLabel161.setText(bundle.getString("CrInstrument.jLabel161.text")); 
        jPanel134.add(jLabel161);

        jComboBox47.setEditable(true);
        jComboBox47.setFont(jComboBox47.getFont());
        jComboBox47.setPreferredSize(new java.awt.Dimension(129, 25));
        jPanel134.add(jComboBox47);

        jPanel69.add(jPanel134);
        jPanel134.setBounds(90, 145, 270, 30);

        jPanel135.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel162.setFont(jLabel162.getFont());
        jLabel162.setText(bundle.getString("CrInstrument.jLabel162.text")); 
        jPanel135.add(jLabel162);

        jComboBox48.setEditable(true);
        jComboBox48.setFont(jComboBox48.getFont());
        jComboBox48.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox48.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox48ItemStateChanged(evt);
            }
        });
        jPanel135.add(jComboBox48);

        jPanel69.add(jPanel135);
        jPanel135.setBounds(90, 80, 270, 30);

        jPanel49.add(jPanel69);
        jPanel69.setBounds(210, 30, 800, 240);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.jPanel49.TabConstraints.tabTitle"), jPanel49); 

        jPanel50.setFont(jPanel50.getFont());
        jPanel50.setLayout(null);

        chartList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartListMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(chartList);

        jPanel50.add(jScrollPane9);
        jScrollPane9.setBounds(20, 50, 70, 220);

        jLabel82.setFont(jLabel82.getFont());
        jLabel82.setText(bundle.getString("CrInstrument.jLabel82.text")); 
        jPanel50.add(jLabel82);
        jLabel82.setBounds(20, 30, 70, 20);

        jPanel93.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton35.setFont(jButton35.getFont());
        jButton35.setText(bundle.getString("CrInstrument.jButton35.text")); 
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel93.add(jButton35);

        jButton36.setFont(jButton36.getFont());
        jButton36.setText(bundle.getString("CrInstrument.jButton36.text")); 
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel93.add(jButton36);

        jButton37.setFont(jButton37.getFont());
        jButton37.setText(bundle.getString("CrInstrument.jButton37.text")); 
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel93.add(jButton37);

        jLabel146.setFont(jLabel146.getFont());
        jLabel146.setText(bundle.getString("CrInstrument.jLabel146.text")); 
        jPanel93.add(jLabel146);

        jTextField44.setFont(jTextField44.getFont());
        jTextField44.setText(bundle.getString("CrInstrument.jTextField44.text")); 
        jTextField44.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel93.add(jTextField44);

        jLabel84.setFont(jLabel84.getFont());
        jLabel84.setText(bundle.getString("CrInstrument.jLabel84.text")); 
        jPanel93.add(jLabel84);

        jComboBox42.setEditable(true);
        jComboBox42.setFont(jComboBox42.getFont());
        jPanel93.add(jComboBox42);

        jPanel50.add(jPanel93);
        jPanel93.setBounds(110, 30, 890, 40);

        jPanel96.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel115.setFont(jLabel115.getFont());
        jLabel115.setText(bundle.getString("CrInstrument.jLabel115.text")); 
        jPanel96.add(jLabel115);

        jLabel116.setFont(jLabel116.getFont());
        jLabel116.setText(bundle.getString("CrInstrument.jLabel116.text")); 
        jPanel96.add(jLabel116);

        jTextField33.setText(bundle.getString("CrInstrument.jTextField33.text")); 
        jTextField33.setPreferredSize(new java.awt.Dimension(106, 25));
        jPanel96.add(jTextField33);

        jLabel117.setFont(jLabel117.getFont());
        jLabel117.setText(bundle.getString("CrInstrument.jLabel117.text")); 
        jPanel96.add(jLabel117);

        jTextField34.setText(bundle.getString("CrInstrument.jTextField34.text")); 
        jTextField34.setPreferredSize(new java.awt.Dimension(106, 25));
        jPanel96.add(jTextField34);

        jCheckBox12.setFont(jCheckBox12.getFont());
        jCheckBox12.setText(bundle.getString("CrInstrument.jCheckBox12.text")); 
        jPanel96.add(jCheckBox12);

        jPanel50.add(jPanel96);
        jPanel96.setBounds(120, 110, 880, 40);

        jPanel97.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel118.setFont(jLabel118.getFont());
        jLabel118.setText(bundle.getString("CrInstrument.jLabel118.text")); 
        jPanel97.add(jLabel118);

        buttonGroup4.add(jRadioButton2);
        jRadioButton2.setFont(jRadioButton2.getFont());
        jRadioButton2.setText(bundle.getString("CrInstrument.jRadioButton2.text")); 
        jPanel97.add(jRadioButton2);

        jTextField35.setText(bundle.getString("CrInstrument.jTextField35.text")); 
        jTextField35.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel97.add(jTextField35);

        jLabel119.setFont(jLabel119.getFont());
        jLabel119.setText(bundle.getString("CrInstrument.jLabel119.text")); 
        jPanel97.add(jLabel119);

        buttonGroup4.add(jRadioButton3);
        jRadioButton3.setFont(jRadioButton3.getFont());
        jRadioButton3.setText(bundle.getString("CrInstrument.jRadioButton3.text")); 
        jPanel97.add(jRadioButton3);

        jTextField36.setText(bundle.getString("CrInstrument.jTextField36.text")); 
        jTextField36.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel97.add(jTextField36);

        jLabel120.setFont(jLabel120.getFont());
        jLabel120.setText(bundle.getString("CrInstrument.jLabel120.text")); 
        jPanel97.add(jLabel120);

        jTextField37.setText(bundle.getString("CrInstrument.jTextField37.text")); 
        jTextField37.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel97.add(jTextField37);

        jLabel121.setFont(jLabel121.getFont());
        jLabel121.setText(bundle.getString("CrInstrument.jLabel121.text")); 
        jPanel97.add(jLabel121);

        jTextField38.setText(bundle.getString("CrInstrument.jTextField38.text")); 
        jTextField38.setPreferredSize(new java.awt.Dimension(36, 25));
        jPanel97.add(jTextField38);

        jLabel122.setFont(jLabel122.getFont());
        jLabel122.setText(bundle.getString("CrInstrument.jLabel122.text")); 
        jPanel97.add(jLabel122);

        jPanel50.add(jPanel97);
        jPanel97.setBounds(150, 190, 840, 40);

        jPanel98.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel123.setFont(jLabel123.getFont());
        jLabel123.setText(bundle.getString("CrInstrument.jLabel123.text")); 
        jPanel98.add(jLabel123);

        buttonGroup5.add(jRadioButton4);
        jRadioButton4.setFont(jRadioButton4.getFont());
        jRadioButton4.setText(bundle.getString("CrInstrument.jRadioButton4.text")); 
        jPanel98.add(jRadioButton4);

        buttonGroup5.add(jRadioButton5);
        jRadioButton5.setFont(jRadioButton5.getFont());
        jRadioButton5.setText(bundle.getString("CrInstrument.jRadioButton5.text")); 
        jPanel98.add(jRadioButton5);

        jPanel50.add(jPanel98);
        jPanel98.setBounds(150, 230, 510, 40);

        jPanel99.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel124.setFont(jLabel124.getFont());
        jLabel124.setText(bundle.getString("CrInstrument.jLabel124.text")); 
        jPanel99.add(jLabel124);

        jCheckBox15.setFont(jCheckBox15.getFont());
        jCheckBox15.setText(bundle.getString("CrInstrument.jCheckBox15.text")); 
        jPanel99.add(jCheckBox15);

        jCheckBox16.setFont(jCheckBox16.getFont());
        jCheckBox16.setText(bundle.getString("CrInstrument.jCheckBox16.text")); 
        jPanel99.add(jCheckBox16);

        jPanel50.add(jPanel99);
        jPanel99.setBounds(150, 310, 550, 40);

        jPanel100.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox19.setFont(jCheckBox19.getFont());
        jCheckBox19.setText(bundle.getString("CrInstrument.jCheckBox19.text")); 
        jPanel100.add(jCheckBox19);

        jCheckBox20.setFont(jCheckBox20.getFont());
        jCheckBox20.setText(bundle.getString("CrInstrument.jCheckBox20.text")); 
        jPanel100.add(jCheckBox20);

        jPanel50.add(jPanel100);
        jPanel100.setBounds(170, 350, 530, 33);

        jPanel101.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel129.setFont(jLabel129.getFont());
        jLabel129.setText(bundle.getString("CrInstrument.jLabel129.text")); 
        jPanel101.add(jLabel129);

        jCheckBox21.setFont(jCheckBox21.getFont());
        jCheckBox21.setText(bundle.getString("CrInstrument.jCheckBox21.text")); 
        jPanel101.add(jCheckBox21);

        jCheckBox22.setFont(jCheckBox22.getFont());
        jCheckBox22.setText(bundle.getString("CrInstrument.jCheckBox22.text")); 
        jPanel101.add(jCheckBox22);

        jPanel50.add(jPanel101);
        jPanel101.setBounds(30, 390, 670, 40);

        jPanel103.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel131.setFont(jLabel131.getFont());
        jLabel131.setText(bundle.getString("CrInstrument.jLabel131.text")); 
        jPanel103.add(jLabel131);

        jLabel132.setFont(jLabel132.getFont());
        jLabel132.setText(bundle.getString("CrInstrument.jLabel132.text")); 
        jPanel103.add(jLabel132);

        jTextField47.setText(bundle.getString("CrInstrument.jTextField47.text")); 
        jTextField47.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField47);

        jLabel133.setFont(jLabel133.getFont());
        jLabel133.setText(bundle.getString("CrInstrument.jLabel133.text")); 
        jPanel103.add(jLabel133);

        jTextField48.setText(bundle.getString("CrInstrument.jTextField48.text")); 
        jTextField48.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField48);

        jLabel134.setFont(jLabel134.getFont());
        jLabel134.setText(bundle.getString("CrInstrument.jLabel134.text")); 
        jPanel103.add(jLabel134);

        jTextField49.setText(bundle.getString("CrInstrument.jTextField49.text")); 
        jTextField49.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField49);

        jLabel135.setFont(jLabel135.getFont());
        jLabel135.setText(bundle.getString("CrInstrument.jLabel135.text")); 
        jPanel103.add(jLabel135);

        jTextField50.setText(bundle.getString("CrInstrument.jTextField50.text")); 
        jTextField50.setPreferredSize(new java.awt.Dimension(56, 25));
        jPanel103.add(jTextField50);

        jLabel136.setFont(jLabel136.getFont());
        jLabel136.setText(bundle.getString("CrInstrument.jLabel136.text")); 
        jPanel103.add(jLabel136);

        jPanel50.add(jPanel103);
        jPanel103.setBounds(120, 70, 880, 30);

        jButton44.setFont(jButton44.getFont());
        jButton44.setText(bundle.getString("CrInstrument.jButton44.text")); 
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel50.add(jButton44);
        jButton44.setBounds(20, 270, 120, 30);

        jButton45.setFont(jButton45.getFont());
        jButton45.setText(bundle.getString("CrInstrument.jButton45.text")); 
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        jPanel50.add(jButton45);
        jButton45.setBounds(20, 300, 120, 30);

        jPanel106.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel143.setFont(jLabel143.getFont());
        jLabel143.setText(bundle.getString("CrInstrument.jLabel143.text")); 
        jPanel106.add(jLabel143);

        jLabel114.setFont(jLabel114.getFont());
        jLabel114.setText(bundle.getString("CrInstrument.jLabel114.text")); 
        jPanel106.add(jLabel114);

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel(colors));
        jComboBox35.setRenderer(new MyCellRenderer());
        jPanel106.add(jComboBox35);

        jLabel138.setFont(jLabel138.getFont());
        jLabel138.setText(bundle.getString("CrInstrument.jLabel138.text")); 
        jPanel106.add(jLabel138);

        jTextField51.setText(bundle.getString("CrInstrument.jTextField51.text")); 
        jTextField51.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel106.add(jTextField51);

        jButton42.setFont(jButton42.getFont());
        jButton42.setText(bundle.getString("CrInstrument.jButton42.text")); 
        jPanel106.add(jButton42);

        jPanel50.add(jPanel106);
        jPanel106.setBounds(30, 530, 730, 40);

        jPanel111.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox24.setFont(jCheckBox24.getFont());
        jCheckBox24.setText(bundle.getString("CrInstrument.jCheckBox24.text")); 
        jPanel111.add(jCheckBox24);

        jCheckBox26.setFont(jCheckBox26.getFont());
        jCheckBox26.setText(bundle.getString("CrInstrument.jCheckBox26.text")); 
        jPanel111.add(jCheckBox26);

        jTextField52.setText(bundle.getString("CrInstrument.jTextField52.text")); 
        jTextField52.setPreferredSize(new java.awt.Dimension(156, 25));
        jPanel111.add(jTextField52);

        jPanel50.add(jPanel111);
        jPanel111.setBounds(30, 480, 670, 35);

        jPanel142.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 255)));
        jPanel142.setLayout(new java.awt.BorderLayout());
        jPanel50.add(jPanel142);
        jPanel142.setBounds(710, 260, 270, 260);

        jPanel112.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox23.setFont(jCheckBox23.getFont());
        jCheckBox23.setText(bundle.getString("CrInstrument.jCheckBox23.text")); 
        jPanel112.add(jCheckBox23);

        jTextField43.setText(bundle.getString("CrInstrument.jTextField43.text")); 
        jTextField43.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel112.add(jTextField43);

        jPanel50.add(jPanel112);
        jPanel112.setBounds(90, 430, 580, 40);

        jPanel148.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox13.setFont(jCheckBox13.getFont());
        jCheckBox13.setText(bundle.getString("CrInstrument.jCheckBox13.text")); 
        jPanel148.add(jCheckBox13);

        jPanel50.add(jPanel148);
        jPanel148.setBounds(230, 150, 550, 30);

        jPanel149.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBox14.setFont(jCheckBox14.getFont());
        jCheckBox14.setText(bundle.getString("CrInstrument.jCheckBox14.text")); 
        jPanel149.add(jCheckBox14);

        jPanel50.add(jPanel149);
        jPanel149.setBounds(290, 270, 390, 30);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.jPanel50.TabConstraints.tabTitle"), jPanel50); 

        jPanel51.setFont(jPanel51.getFont());
        jPanel51.setLayout(null);

        jPanel92.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel169.setFont(jLabel169.getFont());
        jLabel169.setText(bundle.getString("CrInstrument.jLabel169.text")); 
        jPanel92.add(jLabel169);

        jComboBox40.setEditable(true);
        jComboBox40.setFont(jComboBox40.getFont());
        jComboBox40.setPreferredSize(new java.awt.Dimension(204, 25));
        jComboBox40.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox40ItemStateChanged(evt);
            }
        });
        jComboBox40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox40ActionPerformed(evt);
            }
        });
        jPanel92.add(jComboBox40);

        jLabel170.setFont(jLabel170.getFont());
        jLabel170.setText(bundle.getString("CrInstrument.jLabel170.text")); 
        jPanel92.add(jLabel170);

        jComboBox50.setEditable(true);
        jComboBox50.setFont(jComboBox50.getFont());
        jComboBox50.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox50.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox50ItemStateChanged(evt);
            }
        });
        jPanel92.add(jComboBox50);

        jLabel171.setFont(jLabel171.getFont());
        jLabel171.setText(bundle.getString("CrInstrument.jLabel171.text")); 
        jPanel92.add(jLabel171);

        jComboBox51.setEditable(true);
        jComboBox51.setFont(jComboBox51.getFont());
        jComboBox51.setPreferredSize(new java.awt.Dimension(129, 25));
        jComboBox51.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox51ItemStateChanged(evt);
            }
        });
        jPanel92.add(jComboBox51);

        jPanel51.add(jPanel92);
        jPanel92.setBounds(90, 70, 910, 40);

        curveList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                curveListMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(curveList);

        jPanel51.add(jScrollPane10);
        jScrollPane10.setBounds(14, 50, 60, 190);

        jLabel109.setFont(jLabel109.getFont());
        jLabel109.setText(bundle.getString("CrInstrument.jLabel109.text")); 
        jPanel51.add(jLabel109);
        jLabel109.setBounds(20, 30, 60, 15);

        jPanel95.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton38.setFont(jButton38.getFont());
        jButton38.setText(bundle.getString("CrInstrument.jButton38.text")); 
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel95.add(jButton38);

        jButton39.setFont(jButton39.getFont());
        jButton39.setText(bundle.getString("CrInstrument.jButton39.text")); 
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel95.add(jButton39);

        jButton40.setFont(jButton40.getFont());
        jButton40.setText(bundle.getString("CrInstrument.jButton40.text")); 
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel95.add(jButton40);

        jPanel51.add(jPanel95);
        jPanel95.setBounds(90, 30, 670, 40);

        jPanel102.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel103.setFont(jLabel103.getFont());
        jLabel103.setText(bundle.getString("CrInstrument.jLabel103.text")); 
        jPanel102.add(jLabel103);

        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trend" }));
        jPanel102.add(jComboBox33);

        jPanel51.add(jPanel102);
        jPanel102.setBounds(90, 150, 660, 40);

        jButton46.setFont(jButton46.getFont());
        jButton46.setText(bundle.getString("CrInstrument.jButton46.text")); 
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel51.add(jButton46);
        jButton46.setBounds(10, 240, 120, 30);

        jButton47.setFont(jButton47.getFont());
        jButton47.setText(bundle.getString("CrInstrument.jButton47.text")); 
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel51.add(jButton47);
        jButton47.setBounds(10, 270, 120, 30);

        jPanel105.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel106.setFont(jLabel106.getFont());
        jLabel106.setText(bundle.getString("CrInstrument.jLabel106.text")); 
        jPanel105.add(jLabel106);

        jComboBox34.setEditable(true);
        jComboBox34.setFont(jComboBox34.getFont());
        jPanel105.add(jComboBox34);

        jLabel89.setFont(jLabel89.getFont());
        jLabel89.setText(bundle.getString("CrInstrument.jLabel89.text")); 
        jPanel105.add(jLabel89);

        jLabel91.setBackground(new java.awt.Color(255, 255, 255));
        jLabel91.setText(bundle.getString("CrInstrument.jLabel91.text")); 
        jLabel91.setOpaque(true);
        jPanel105.add(jLabel91);

        jLabel172.setFont(jLabel172.getFont());
        jLabel172.setText(bundle.getString("CrInstrument.jLabel172.text")); 
        jPanel105.add(jLabel172);

        jComboBox52.setEditable(true);
        jComboBox52.setFont(jComboBox52.getFont());
        jComboBox52.setPreferredSize(new java.awt.Dimension(129, 25));
        jPanel105.add(jComboBox52);

        jPanel51.add(jPanel105);
        jPanel105.setBounds(90, 110, 880, 40);

        jPanel107.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel144.setFont(jLabel144.getFont());
        jLabel144.setText(bundle.getString("CrInstrument.jLabel144.text")); 
        jPanel107.add(jLabel144);

        jLabel145.setFont(jLabel145.getFont());
        jLabel145.setText(bundle.getString("CrInstrument.jLabel145.text")); 
        jPanel107.add(jLabel145);

        jComboBox38.setModel(new javax.swing.DefaultComboBoxModel(colors));
        jComboBox38.setRenderer(new MyCellRenderer());
        jPanel107.add(jComboBox38);

        jPanel51.add(jPanel107);
        jPanel107.setBounds(90, 190, 810, 40);

        jTabbedPane3.addTab(bundle.getString("CrInstrument.jPanel51.TabConstraints.tabTitle"), jPanel51); 

        jPanel3.add(jTabbedPane3, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(bundle.getString("CrInstrument.jPanel3.TabConstraints.tabTitle"), jPanel3); 

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText(bundle.getString("CrInstrument.jMenu1.text")); 
        jMenu1.setFont(jMenu1.getFont());
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        fileUpLoadMenuItem.setFont(fileUpLoadMenuItem.getFont());
        fileUpLoadMenuItem.setText(bundle.getString("CrInstrument.fileUpLoadMenuItem.text")); 
        fileUpLoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileUpLoadMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(fileUpLoadMenuItem);

        jMenuItem9.setFont(jMenuItem9.getFont());
        jMenuItem9.setText(bundle.getString("CrInstrument.jMenuItem9.text")); 
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem1.setFont(jMenuItem1.getFont());
        jMenuItem1.setText(bundle.getString("CrInstrument.jMenuItem1.text")); 
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        fileMenuItem01.setFont(fileMenuItem01.getFont());
        fileMenuItem01.setText(bundle.getString("CrInstrument.fileMenuItem01.text")); 
        fileMenuItem01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuItem01ActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuItem01);

        fileMenuItem02.setFont(fileMenuItem02.getFont());
        fileMenuItem02.setText(bundle.getString("CrInstrument.fileMenuItem02.text")); 
        fileMenuItem02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuItem02ActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuItem02);

        fileMenuItem03.setFont(fileMenuItem03.getFont());
        fileMenuItem03.setText(bundle.getString("CrInstrument.fileMenuItem03.text")); 
        fileMenuItem03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuItem03ActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuItem03);

        fileMenuItem04.setFont(fileMenuItem04.getFont());
        fileMenuItem04.setText(bundle.getString("CrInstrument.fileMenuItem04.text")); 
        fileMenuItem04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuItem04ActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuItem04);

        fileMenuItem05.setFont(fileMenuItem05.getFont());
        fileMenuItem05.setText(bundle.getString("CrInstrument.fileMenuItem05.text")); 
        fileMenuItem05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuItem05ActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuItem05);

        jMenuItem2.setFont(jMenuItem2.getFont());
        jMenuItem2.setText(bundle.getString("CrInstrument.jMenuItem2.text")); 
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("CrInstrument.jMenu2.text")); 
        jMenu2.setFont(jMenu2.getFont());

        jMenuItem3.setFont(jMenuItem3.getFont());
        jMenuItem3.setText(bundle.getString("CrInstrument.jMenuItem3.text")); 
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setFont(jMenuItem4.getFont());
        jMenuItem4.setText(bundle.getString("CrInstrument.jMenuItem4.text")); 
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        toolMenuItem01.setFont(toolMenuItem01.getFont());
        toolMenuItem01.setText(bundle.getString("CrInstrument.toolMenuItem01.text")); 
        toolMenuItem01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolMenuItem01ActionPerformed(evt);
            }
        });
        jMenu2.add(toolMenuItem01);

        toolMenuItem02.setFont(toolMenuItem02.getFont());
        toolMenuItem02.setText(bundle.getString("CrInstrument.toolMenuItem02.text")); 
        toolMenuItem02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolMenuItem02ActionPerformed(evt);
            }
        });
        jMenu2.add(toolMenuItem02);

        toolMenuItem03.setFont(toolMenuItem03.getFont());
        toolMenuItem03.setText(bundle.getString("CrInstrument.toolMenuItem03.text")); 
        toolMenuItem03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolMenuItem03ActionPerformed(evt);
            }
        });
        jMenu2.add(toolMenuItem03);

        toolMenuItem04.setFont(toolMenuItem04.getFont());
        toolMenuItem04.setText(bundle.getString("CrInstrument.toolMenuItem04.text")); 
        toolMenuItem04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolMenuItem04ActionPerformed(evt);
            }
        });
        jMenu2.add(toolMenuItem04);

        toolMenuItem05.setFont(toolMenuItem05.getFont());
        toolMenuItem05.setText(bundle.getString("CrInstrument.toolMenuItem05.text")); 
        toolMenuItem05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolMenuItem05ActionPerformed(evt);
            }
        });
        jMenu2.add(toolMenuItem05);

        jMenuItem6.setFont(jMenuItem6.getFont());
        jMenuItem6.setText(bundle.getString("CrInstrument.jMenuItem6.text")); 
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText(bundle.getString("CrInstrument.jMenu3.text")); 
        jMenu3.setFont(jMenu3.getFont());

        jMenuItem8.setFont(jMenuItem8.getFont());
        jMenuItem8.setText(bundle.getString("CrInstrument.jMenuItem8.text")); 
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem26.setFont(jMenuItem26.getFont());
        jMenuItem26.setText(bundle.getString("CrInstrument.jMenuItem26.text")); 
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem26);

        jMenuItem27.setFont(jMenuItem27.getFont());
        jMenuItem27.setText(bundle.getString("CrInstrument.jMenuItem27.text")); 
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem27);

        jMenuItem20.setFont(jMenuItem20.getFont());
        jMenuItem20.setText(bundle.getString("CrInstrument.jMenuItem20.text")); 
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem20);

        helpMenuItem01.setFont(helpMenuItem01.getFont());
        helpMenuItem01.setText(bundle.getString("CrInstrument.helpMenuItem01.text")); 
        helpMenuItem01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItem01ActionPerformed(evt);
            }
        });
        jMenu3.add(helpMenuItem01);

        helpMenuItem02.setFont(helpMenuItem02.getFont());
        helpMenuItem02.setText(bundle.getString("CrInstrument.helpMenuItem02.text")); 
        helpMenuItem02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItem02ActionPerformed(evt);
            }
        });
        jMenu3.add(helpMenuItem02);

        helpMenuItem03.setFont(helpMenuItem03.getFont());
        helpMenuItem03.setText(bundle.getString("CrInstrument.helpMenuItem03.text")); 
        helpMenuItem03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItem03ActionPerformed(evt);
            }
        });
        jMenu3.add(helpMenuItem03);

        helpMenuItem04.setFont(helpMenuItem04.getFont());
        helpMenuItem04.setText(bundle.getString("CrInstrument.helpMenuItem04.text")); 
        helpMenuItem04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItem04ActionPerformed(evt);
            }
        });
        jMenu3.add(helpMenuItem04);

        helpMenuItem05.setFont(helpMenuItem05.getFont());
        helpMenuItem05.setText(bundle.getString("CrInstrument.helpMenuItem05.text")); 
        helpMenuItem05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItem05ActionPerformed(evt);
            }
        });
        jMenu3.add(helpMenuItem05);

        jMenuItem7.setFont(jMenuItem7.getFont());
        jMenuItem7.setText(bundle.getString("CrInstrument.jMenuItem7.text")); 
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }

    private void btnLogoutAdminActionPerformed(java.awt.event.ActionEvent evt) {
      jTabbedPane1.remove(jPanel3);
      adminLogin = false;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
      String pw1 = new String(jPasswordField1.getPassword());
      String pw2 = new String(jPasswordField2.getPassword());
      String pw3 = new String(jPasswordField3.getPassword());

      if (YB642D.decode(props.getProperty("mgnt-pw")).equals(pw1)) {
        if (pw2.equals(pw3)) {

          props.setProperty("mgnt-pw", YB642E.encode(pw2));

          JOptionPane.showMessageDialog(this,bundle2.getString("CrInstrument.xy.msg106"));
        } else {
          JOptionPane.showMessageDialog(this,bundle2.getString("CrInstrument.xy.msg107"));
        }
      } else {
        JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg108"));
      }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {

      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

      if(confirmExit()) setVisible(false);

    }

    private void stationListMouseReleased(java.awt.event.MouseEvent evt) {
      String selectedItem = (String)stationList.getSelectedValue();

      showStation(selectedItem);
    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
      pressStartBtn(1);
    }

    void pressStartBtn(int type){
        if (!updatePorts(type)) {
        if(type==1) JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg59"));
        else sysLog(bundle2.getString("CrInstrument.xy.msg60"));
        return;
      }
      if (!openPorts(type)) {
        if(type==1) JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg61"));
        else sysLog(bundle2.getString("CrInstrument.xy.msg62"));
        return;
      }
      if (!connected) {
        try {
          Thread.sleep(1500L);
        } catch (InterruptedException e) {
        }

        lightPanel2.setColor(Color.green, Color.green);
        connected = true;
      }
      if (onlyReceiveCB.isSelected()) {
        if(type==1) JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg63"));
        return;
      }
      if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
        sysLog("press " + btnStart.getText() + ", connected=" + connected + ", continueMonitor=" + continueMonitor + ", coorToPorts.size()=" + coorToPorts.size());
      }
      if (btnStart.getText().trim().equals(bundle2.getString("CrInstrument.xy.msg3"))) {
        eventThread.setStatus(wn.w.getGNS(1),"",13);
        continueMonitor = true;
        setBegin();

        btnConnect.setEnabled(false);

        lightPanel2.setColor(Color.green, Color.green);
        currentStat = 0;
        currentLightStat = 0;
        btnStart.setText(bundle2.getString("CrInstrument.xy.msg4"));
        saveRecordThread.setBegin();
        eventThread.setStatus(wn.w.getGNS(1),"",14);
      } else {
        int an = JOptionPane.showConfirmDialog(this,bundle2.getString("CrInstrument.xy.msg64"), bundle2.getString("CrInstrument.xy.msg65"), JOptionPane.YES_OPTION | JOptionPane.CANCEL_OPTION);
        if (an == JOptionPane.YES_OPTION) {
          eventThread.setStatus(wn.w.getGNS(1),"",15);
          continueMonitor = false;
          button02.setEnabled(true);
          btnConnect.setEnabled(true);

          currentStat = 9;
          currentLightStat = 9;
          btnStart.setText(bundle2.getString("CrInstrument.xy.msg3"));
          eventThread.setStatus(wn.w.getGNS(1),"",16);
        }
      }
}

    private void button02ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",26);
    }

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {

    }

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
      int row = jTable2.getSelectedRow();
      int column = jTable2.getSelectedColumn();
      String device = (String) jTable2.getModel().getValueAt(row, 0);
      String model = (String) jTable2.getModel().getValueAt(row, 1);
      String SN = (String) jTable2.getModel().getValueAt(row, 2);
      String dataName = (String) jTable2.getModel().getValueAt(row, 3);
      String id=device+","+model+","+SN+","+dataName;
      if (id != null && id.length() > 0) {
        currentTable2Row = row;
        currentSensorID = currentStation + "," + id;
        showSensorData(row);
        jTable2.repaint();
        showCurve(currentSensorID);
      }
    }
  void showSensorData(int row) {

    if (jTable2.getRowCount() > 0 && row > -1 && row < jTable2.getRowCount()) {
      

      if (sensors.get(currentSensorID) != null) {
        String info2[] = ylib.csvlinetoarray((String) sensors.get(currentSensorID));
        jLabel20.setText(info2[0] );
        jLabel34.setText(info2[1]+ " (" + info2[2] + ") - "+info2[3]);
        jLabel35.setText(info2[4]+":" );
        jLabel30.setText(info2[20] + " "+info2[9]);
      }
    }
  }

  private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!adminLogin && !userLogin) {
      if (userDialog == null) {
        userDialog = new UserDialog(this, true);
      }
      userDialog.setVisible(true);
    }
    if (adminLogin || userLogin) {
      updatePorts(1);
      saveStations();
      updateProps_miscellaneous();

      jTabbedPane1.setSelectedComponent(jPanel1);
      

    } else {
      updateUIFromProps();
    }
  }

  private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!adminLogin && !userLogin) {
      if (userDialog == null) {
        userDialog = new UserDialog(this, true);
      }
      userDialog.setVisible(true);
    }
    if (adminLogin || userLogin) {
      updateProps_options();
      jTabbedPane1.setSelectedComponent(jPanel1);

      

    } else {
      updateUIFromProps();
    }
  }

  private void btnTestEMailActionPerformed(java.awt.event.ActionEvent evt) {
    jLabel25.setText(bundle2.getString("CrInstrument.xy.msg66"));
    if (sendEmail(2,"test '"+getPropsString("email-sp")+"' email","test email")) {
      jLabel25.setText("");
      JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg67")+" \"" + jTextField6.getText() + "\" "+bundle2.getString("CrInstrument.xy.msg68"));
    } else {
      jLabel25.setText("");
      if(getPropsString("email-sp").equalsIgnoreCase("gmail")){
       JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg69")+"\r\n" 
              + bundle2.getString("CrInstrument.xy.msg70")+" \r\n"+bundle2.getString("CrInstrument.xy.msg71")+" \r\n"+bundle2.getString("CrInstrument.xy.msg72")+"\r\n" 
              + bundle2.getString("CrInstrument.xy.msg73")+"\r\n\t"+bundle2.getString("CrInstrument.xy.msg74"));  
      } else {
        JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg75")+"\r\n"  
              + bundle2.getString("CrInstrument.xy.msg76")+" \r\n"+bundle2.getString("CrInstrument.xy.msg77")+" \r\n"+bundle2.getString("CrInstrument.xy.msg78")+"\r\n"  
              + bundle2.getString("CrInstrument.xy.msg79")+"\r\n\t"+bundle2.getString("CrInstrument.xy.msg80"));  
      }
    }
  }

  private void btnTestSMSActionPerformed(java.awt.event.ActionEvent evt) {
    jLabel19.setText(bundle2.getString("CrInstrument.xy.msg81"));
    sendSms(2,"test '"+this.getPropsString("sms-sp")+"' sms");
    jLabel19.setText("");
    

  }

  private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {

    if (evt.getKeyCode() == 10) {
      int row = jTable1.getSelectedRow();
      int column = jTable1.getSelectedColumn();

      String key = (String) jTable1.getModel().getValueAt(row, 0) + "," + (String) jTable1.getModel().getValueAt(row, 3);
      

    }
  }

  boolean updateFromSensorTable() {
    int rowCount = jTable1.getRowCount();

    Vector keys = new Vector();
    for (int row = 0; row < rowCount; row++) {
      if (jTable1.getModel().getValueAt(row, 0)!=null && jTable1.getModel().getValueAt(row, 1)!=null && jTable1.getModel().getValueAt(row, 2)!=null && jTable1.getModel().getValueAt(row, 3)!=null && jTable1.getModel().getValueAt(row, 4)!=null) {
      if (((String) jTable1.getModel().getValueAt(row, 0)).trim().length() > 0 && ((String) jTable1.getModel().getValueAt(row, 1)).trim().length() > 0 && ((String) jTable1.getModel().getValueAt(row, 2)).trim().length() > 0 && ((String) jTable1.getModel().getValueAt(row, 3)).trim().length() > 0 && ((String) jTable1.getModel().getValueAt(row, 4)).trim().length() > 0) {
        String key = (String) jTable1.getModel().getValueAt(row, 0) + "," + (String) jTable1.getModel().getValueAt(row, 1) + "," + (String) jTable1.getModel().getValueAt(row, 2) + "," + (String) jTable1.getModel().getValueAt(row, 3) + "," + (String) jTable1.getModel().getValueAt(row, 4);
        if (keys.contains(key)) {
          JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg82") + key + bundle2.getString("CrInstrument.xy.msg83"));
          return false;
        } else {
          keys.add(key);
        }
      }
      }
    }
    TreeMap newSensors = new TreeMap();
    TreeMap newDeviceToKeys = new TreeMap();
    TreeMap newKeyToDevices = new TreeMap();

    coorDevices.clear();
    for (int row = 0; row < rowCount; row++) {
      if (jTable1.getModel().getValueAt(row, 0)!=null && jTable1.getModel().getValueAt(row, 1)!=null
              && jTable1.getModel().getValueAt(row, 2)!=null && jTable1.getModel().getValueAt(row, 3)!=null && jTable1.getModel().getValueAt(row, 4)!=null
              && ((String) jTable1.getModel().getValueAt(row, 0)).trim().length() > 0 && ((String) jTable1.getModel().getValueAt(row, 1)).trim().length() > 0
              && ((String) jTable1.getModel().getValueAt(row, 2)).trim().length() > 0 && ((String) jTable1.getModel().getValueAt(row, 3)).trim().length() > 0
              && ((String) jTable1.getModel().getValueAt(row, 4)).trim().length() > 0) {
        String id21 = (String) rowToRandomID.get("" + row);
        String info[] = null;
        if (id21 != null) {
          info = getDeviceInfoFrom21(id21);
        }
        if (id21 == null || info == null || info.length < 28) {
          info = new String[]{((String) jTable1.getModel().getValueAt(row, 0)).trim(), ((String) jTable1.getModel().getValueAt(row, 3)).trim(),
            ((String) jTable1.getModel().getValueAt(row, 4)).trim(),
            (jTable1.getModel().getValueAt(row, 5)==null? "":((String) jTable1.getModel().getValueAt(row, 5)).trim()),
            (jTable1.getModel().getValueAt(row, 6)==null? "":((String) jTable1.getModel().getValueAt(row, 6)).trim()), 
            (jTable1.getModel().getValueAt(row, 10)==null? "":((String) jTable1.getModel().getValueAt(row, 10)).trim()),
            (jTable1.getModel().getValueAt(row, 11)==null? "":((String) jTable1.getModel().getValueAt(row, 11)).trim()), 
            (jTable1.getModel().getValueAt(row, 12)==null? "":((String) jTable1.getModel().getValueAt(row, 12)).trim()),
            (jTable1.getModel().getValueAt(row, 13)==null? "":((String) jTable1.getModel().getValueAt(row, 13)).trim()), "",
            "0.0", "0.0",
            "0.0", "0.0",
            ((String) jTable1.getModel().getValueAt(row, 1)).trim(), ((String) jTable1.getModel().getValueAt(row, 2)).trim(),
            "", "0.0",
            "0.0", "0.0",
            "0.0", "" + Math.round(Math.random() * 100000000.0D),
            "0", "",
            "0", "1",
            (jTable1.getModel().getValueAt(row, 7)==null? "":((String) jTable1.getModel().getValueAt(row, 7)).trim()), 
            (jTable1.getModel().getValueAt(row, 8)==null? "":((String) jTable1.getModel().getValueAt(row, 8)).trim()),
            (jTable1.getModel().getValueAt(row, 9)==null? "":((String) jTable1.getModel().getValueAt(row, 9)).trim())};
          rowToRandomID.put("" + row, info[21]);
        }
        info[0] = (String) jTable1.getModel().getValueAt(row, 0);
        info[1] = (String) jTable1.getModel().getValueAt(row, 1);
        info[2] = (String) jTable1.getModel().getValueAt(row, 2);
        info[3] = (String) jTable1.getModel().getValueAt(row, 3);
        info[4] = (String) jTable1.getModel().getValueAt(row, 4);
        String newKey = info[0] + "," + info[1]+ "," + info[2]+ "," + info[3]+ "," + info[4];
        info[14] = (String) jTable1.getModel().getValueAt(row, 5);
        info[9] = (String) jTable1.getModel().getValueAt(row, 6);
        info[10] = (String) jTable1.getModel().getValueAt(row, 7);
        info[15] = (String) jTable1.getModel().getValueAt(row, 8);
        info[16] = (String) jTable1.getModel().getValueAt(row, 9);
        info[26] = (String) jTable1.getModel().getValueAt(row, 10);
        String tmp=(String) jTable1.getModel().getValueAt(row, 11);
        info[25] = (tmp.equalsIgnoreCase("Yes") || tmp.equalsIgnoreCase("Y")? "0":"1");
        info[5] = (String) jTable1.getModel().getValueAt(row, 12);

        info[6] = (String) jTable1.getModel().getValueAt(row, 13);
        info[7] = (String) jTable1.getModel().getValueAt(row, 14);
        info[8] = (String) jTable1.getModel().getValueAt(row, 15);
        if (info[23].length() > 0 && info[24].length() > 5) {
          String port = (String) stations.get(info[0]);
          if (port != null) {
            String coor = (String) portToCoors.get(port);

            insertToCoorDevices(coor, info);
          }
          newDeviceToKeys.put(info[23], newKey);
          newKeyToDevices.put(newKey, info[23]);
        }

        newSensors.put(newKey, ylib.arrayToCsvLine(info));
      }
    }
    sensors = newSensors;
    deviceToKeys = newDeviceToKeys;
    keyToDevices = newKeyToDevices;
    modifySensors = true;
    jTabbedPane1.setSelectedComponent(jPanel1);
    return true;
  }

  public String[] getDeviceInfoFrom21(String id21) {
    Iterator it = sensors.values().iterator();
    String[] info = {};
    for (; it.hasNext();) {
      info = ylib.csvlinetoarray((String) it.next());
      if (info.length > 21 && info[21].equals(id21)) {
        break;
      }
    }
    return info;
  }

  public String[] getDeviceInfoFrom23(String id23) {
    Iterator it = sensors.values().iterator();
    String[] info = {};
    for (; it.hasNext();) {
      info = ylib.csvlinetoarray((String) it.next());
      if (info.length > 23 && info[23].equals(id23)) {
        break;
      }
    }
    return info;
  }
  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    String oldDir = jTextField1.getText().trim();
    JFileChooser chooser = new JFileChooser(oldDir);
    chooser.setDialogTitle(bundle2.getString("CrInstrument.xy.msg84"));
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int returnVal = chooser.showDialog(this, bundle2.getString("CrInstrument.xy.msg85"));
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      jTextField1.setText(chooser.getSelectedFile().getAbsolutePath());
    }
  }

  private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
    connect();
  }
  void connect() {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("press connect, connected=" + connected);
    }
    if (!updatePorts(1)) {
      return;
    }
    if (!openPorts(1)) {
      return;
    }
    if (!connected) {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
      }

      connected = true;
    }
    lightPanel2.setColor(Color.green, Color.green);

  }
  private void showAlarmRBActionPerformed(java.awt.event.ActionEvent evt) {
    dataUpdated = true;
  }

  private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {

  }

  private void onlyReceiveCBActionPerformed(java.awt.event.ActionEvent evt) {
    if (onlyReceiveCB.isSelected()) {
      props.put("only_receive", "Y");

      if (continueMonitor) {
        continueMonitor = false;
        button02.setEnabled(true);

        currentStat = 9;
        currentLightStat = 9;
        btnStart.setText(bundle2.getString("CrInstrument.xy.msg3"));
      }

      btnStart.setEnabled(false);
    } else {
      button02.setEnabled(true);
      btnStart.setEnabled(true);

      props.put("only_receive", "N");
    }
  }

  private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!adminLogin && !userLogin) {
      if (userDialog == null) {
        userDialog = new UserDialog(this, true);
      }
      userDialog.setVisible(true);
    }
    if (adminLogin || userLogin) {
      int an = JOptionPane.showConfirmDialog(this, bundle2.getString("CrInstrument.xy.msg86"), bundle2.getString("CrInstrument.xy.msg87"), JOptionPane.YES_OPTION | JOptionPane.CANCEL_OPTION);
      if (an == JOptionPane.YES_OPTION) {
        if (onlyReceiveCB.isSelected()) {
          updateHistoryRecord = true;
        } else {
          if (continueMonitor) {
            updateHistoryRecord = true;
          } else {
            updateHistoryFile(9);
          }
        }

      }
    }
  }

  private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {

  }

  private void fileUpLoadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    if (!OneVar.check(props.getProperty("lky-n"), 4)) {
      JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg88"));
      return;
    }
    String oldFile = "";
    JFileChooser chooser = new JFileChooser(oldFile);
    chooser.setDialogTitle(bundle2.getString("CrInstrument.xy.msg89"));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int returnVal = chooser.showDialog(this, bundle2.getString("CrInstrument.xy.msg90"));
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String chooseFile = chooser.getSelectedFile().getAbsolutePath();
    }
  }

  private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {
    String pw2 = new String(jPasswordField9.getPassword());
    String pw3 = new String(jPasswordField8.getPassword());
    if (pw2.equals(pw3)) {
      props.setProperty("setting-pw", YB642E.encode(pw2));

      JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg91"));
    } else {
      JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg92"));
    }
  }

  private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!adminLogin && !userLogin) {
      if (userDialog == null) {
        userDialog = new UserDialog(this, true);
      }
      userDialog.setVisible(true);
    }
    if (adminLogin || userLogin) {
      if (updateFromSensorTable()) {
        updateSensorTable();
        showStation(currentStation);
        if (!continueMonitor && !chkProps("only_receive")) {
          connect();
        }
        saveSensors();
        jTabbedPane1.setSelectedComponent(jPanel1);
        

      } else {
        updateSensorTable();
      }
    } else {
      updateSensorTable();
    }
  }

  private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {
    updateProps_admin();
    jTabbedPane1.setSelectedComponent(jPanel1);

  }

  private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
    int[] rows = jTable4.getSelectedRows();

    for (int i = rows.length - 1; i > -1; i--) {
      model.removeRow(rows[i]);
    }
  }

  private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
    int[] rows = jTable3.getSelectedRows();

    for (int i = rows.length - 1; i > -1; i--) {
      model.removeRow(rows[i]);
    }
  }

  private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {
    ((DefaultTableModel) jTable4.getModel()).addRow(new Object[jTable4.getModel().getColumnCount()]);
  }

  private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {
    ((DefaultTableModel) jTable3.getModel()).addRow(new Object[jTable3.getModel().getColumnCount()]);
  }

  private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
    windowscommand2 wc2 = new windowscommand2("start osk.exe");
    wc2.start();
  }

  private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!OneVar.check(props.getProperty("lky-n"), 4)) {
      JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg93")); 
      return;
    }
  }

  private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!OneVar.check(props.getProperty("lky-n"), 4)) {
      JOptionPane.showMessageDialog(this, bundle2.getString("CrInstrument.xy.msg94"));
      return;
    }
  }

  private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {
    if (roomIndex > 0) {
      roomIndex--;
      dataUpdated = true;
    }
  }

  private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {
    if (roomIndex < (roomValues.length - 1)) {
      roomIndex++;
      dataUpdated = true;
    } 
  }

  private void cbAutoAdjustYActionPerformed(java.awt.event.ActionEvent evt) {
    dataUpdated = true;
  }

  private void cbZeroActionPerformed(java.awt.event.ActionEvent evt) {
    dataUpdated = true;
  }

  private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
   newEvent();
  }
private void newEvent(){
      int cnt=eventList.getModel().getSize();
    int max=0;
    if(cnt>0){
      for(int i=0;i<cnt;i++) {
         String tm=(String)eventList.getModel().getElementAt(i);
         int v=Integer.parseInt(tm.substring(2));
         if(v>max) max=v;
      }
    }
    max=max+1;
    String newItem="EV"+zero(4-String.valueOf(max).length())+max;
    eventListModel.addElement(newItem);
    eventList.setSelectedValue(newItem, true);
    conditionListModel2.removeAllElements();
    actionListModel2.removeAllElements();
    eventTM.put(newItem, newItem+",0,0,");
}
  private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {
    int cnt=conditionList.getModel().getSize();
    int max=0;
    if(cnt>0){
      for(int i=0;i<cnt;i++) {
         String tm=(String)conditionList.getModel().getElementAt(i);
         int v=Integer.parseInt(tm.substring(2));
         if(v>max) max=v;
      }
    }
    max=max+1;
    String newItem="CD"+zero(4-String.valueOf(max).length())+max;
    conditionListModel.addElement(newItem);
    conditionList.setSelectedValue(newItem, true);
    jComboBox14.setSelectedItem("");
    conditionTM.put(newItem,newItem+",");
  }
void updateEventTM(){
  if(eventList.getSelectedIndex()>-1){
     String sel=(String)eventList.getSelectedValue();
     StringBuffer sb=new StringBuffer(sel+","+conditionList2.getModel().getSize()+","+actionList2.getModel().getSize()+",");
     for(int i=0;i<conditionList2.getModel().getSize();i++) sb.append((String)conditionList2.getModel().getElementAt(i)+",");
     for(int i=0;i<actionList2.getModel().getSize();i++) sb.append((String)actionList2.getModel().getElementAt(i)+",");
     eventTM.put(sel, sb.toString());
  }
}
void showEvent(){
  if(eventList.getSelectedIndex()==-1){
    if(eventListModel.size()>0){
      eventList.setSelectedIndex(0);
    } else if(eventTM.size()>0){
        Iterator it=eventTM.keySet().iterator();
        for(;it.hasNext();){
          String key=(String)it.next();
          eventListModel.addElement(key);
        }
        eventList.setSelectedIndex(0);
      }
  }
  if(eventList.getSelectedIndex()>-1){
     String sel=(String)eventList.getSelectedValue();
     conditionListModel2.removeAllElements();
     actionListModel2.removeAllElements();
     if(eventTM.get(sel)!=null){
       String data[]=ylib.csvlinetoarray((String)eventTM.get(sel));
       int cntC=0,cntA=0;
       if(data.length>1 && data[1].length()>0) cntC=Integer.parseInt(data[1]);
       if(data.length>2 && data[2].length()>0) cntA=Integer.parseInt(data[2]);
       if(cntC>0 && data.length>2+cntC) {
         for(int i=0;i<cntC;i++) conditionListModel2.addElement(data[3+i]);
       }
       if(cntA>0 && data.length>2+cntC+cntA) {
         for(int i=0;i<cntA;i++) actionListModel2.addElement(data[3+cntC+i]);
       }
     }
  }
}
void showCondition(){
  if(conditionList.getSelectedIndex()==-1){
    if(conditionListModel.size()>0){
      conditionList.setSelectedIndex(0);
    } else if(conditionTM.size()>0){
        Iterator it=conditionTM.keySet().iterator();
        for(;it.hasNext();){
          String key=(String)it.next();
          conditionListModel.addElement(key);
        }
        conditionList.setSelectedIndex(0);
      }
  }
   showCondition1();
}
void showAction(){
  if(actionList.getSelectedIndex()==-1){
    if(actionListModel.size()>0){
      actionList.setSelectedIndex(0);
    } else if(actionTM.size()>0){
        Iterator it=actionTM.keySet().iterator();
        for(;it.hasNext();){
          String key=(String)it.next();
          actionListModel.addElement(key);
        }
        actionList.setSelectedIndex(0);
      }
  }
  showAction1();
}
  private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {
    getNewAction(null,null);
  }
String getNewAction(String key,String action){
    int cnt=actionList.getModel().getSize();
    int max=0;
    if(cnt>0){
      for(int i=0;i<cnt;i++) {
         String tm=(String)actionList.getModel().getElementAt(i);
         int v=Integer.parseInt(tm.substring(2));
         if(v>max) max=v;
      }
    }
    max=max+1;
    String newItem="AC"+zero(4-String.valueOf(max).length())+max;
    actionListModel.addElement(newItem);
    actionList.setSelectedValue(newItem, true);
    jComboBox19.setSelectedItem("");
    String newInfo[]=new String[actMaxArrCnt];
    for(int i=0;i<newInfo.length;i++) newInfo[i]="";
    newInfo[0]=newItem;
    if(action!=null) {newInfo[2]=action; jComboBox18.setSelectedItem(newInfo[2]);}
    if(key!=null){
      String keyInfo[]=ylib.csvlinetoarray(key);
      if(keyInfo.length>0) {newInfo[1]=keyInfo[0]; jComboBox19.setSelectedItem(newInfo[1]);}
      if(keyInfo.length>1) {newInfo[39]=keyInfo[1]; jComboBox49.setSelectedItem(newInfo[1]);}
      if(keyInfo.length>2) {newInfo[16]=keyInfo[2]; jComboBox44.setSelectedItem(newInfo[1]);}
      if(keyInfo.length>4) {newInfo[17]=keyInfo[4]; jComboBox45.setSelectedItem(newInfo[1]);}
    }
    actionTM.put(newItem,ylib.arrayToCsvLine(newInfo));
    return newItem;
}
  private void eventListMouseClicked(java.awt.event.MouseEvent evt) {
    showEvent();
  }

  private void conditionList2MouseClicked(java.awt.event.MouseEvent evt) {
    showCondition2();
  }

  private void conditionListMouseClicked(java.awt.event.MouseEvent evt) {
    showCondition1();
  }

  private void actionListMouseClicked(java.awt.event.MouseEvent evt) {
    showAction1();
  }

  private void jComboBox16ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      String sel=((String)jComboBox16.getSelectedItem()).trim();
      showConditionItem(sel); 
    }
  }
void showConditionItem(String sel){
        jPanel72.setVisible(false);
        jPanel124.setVisible(false);
      if(sel.equalsIgnoreCase("Data condition"))  jPanel72.setVisible(true);
      else if(sel.equalsIgnoreCase("Data checked by Java class")) jPanel124.setVisible(true);

      jPanel73.setVisible(false);
      jPanel133.setVisible(false);
      jPanel134.setVisible(false);
      jPanel135.setVisible(false);
      if(sel.equalsIgnoreCase("Data condition") || sel.equalsIgnoreCase("Any data") || sel.equalsIgnoreCase("Data checked by Java class")
               || sel.equalsIgnoreCase("Over upper take-action level") || sel.equalsIgnoreCase("Under lower take-action level") 
              || sel.equalsIgnoreCase("Over upper alert level") || sel.equalsIgnoreCase("Under lower alert leve"))  {
      jPanel73.setVisible(true);
      jPanel133.setVisible(true);
      jPanel134.setVisible(true);
      jPanel135.setVisible(true);
      }
}
  private void jComboBox18ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
    String sel=((String)jComboBox18.getSelectedItem()).trim();
    showActionItem(sel);
    }
  }
void showActionItem(String sel){
      jPanel126.setVisible(false);
      jPanel26.setVisible(false);
    jPanel79.setVisible(false);
    jPanel85.setVisible(false);
    jPanel78.setVisible(false);

      if(sel.equalsIgnoreCase("Set data value")) jPanel79.setVisible(true);
      else if(sel.equalsIgnoreCase("Send command")) jPanel85.setVisible(true);
      else if(sel.equalsIgnoreCase("Set device SN")) jPanel78.setVisible(true);
      else if(sel.equalsIgnoreCase("Java class")) jPanel126.setVisible(true);
      else if(sel.equalsIgnoreCase("Open URL")) jPanel26.setVisible(true);

      jPanel76.setVisible(false);
      jPanel136.setVisible(false);
      jPanel122.setVisible(false);
      jPanel132.setVisible(false);
      if(sel.equalsIgnoreCase("Set data value") || sel.equalsIgnoreCase("Send command") || sel.equalsIgnoreCase("Set device SN"))  {
      jPanel76.setVisible(true);
      jPanel136.setVisible(true);
      jPanel122.setVisible(true);
      jPanel132.setVisible(true);
      }

}
  private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {
    if(conditionList.getSelectedIndex()>-1){
      String sel=(String)conditionList.getSelectedValue();
      conditionListModel.removeElement(sel);
      conditionTM.remove(sel);
      eventRemoveCondition(sel);
      showEvent();
    }
  }

  private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {
    if(actionList.getSelectedIndex()>-1){
      String sel=(String)actionList.getSelectedValue();
      actionListModel.removeElement(sel);
      actionTM.remove(sel);
      eventRemoveAction(sel);
      showEvent();
    }
  }

  private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {
    if(conditionList2.getSelectedIndex()>-1){
      String cond=(String)conditionList2.getSelectedValue();
      conditionListModel2.removeElement(cond);
      if(eventList.getSelectedIndex()>-1){
        String sel=(String)eventList.getSelectedValue();
        eventRemoveCondition(sel, cond);
      }
    }
  }

  private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {
    if(actionList2.getSelectedIndex()>-1){
      String act=(String)actionList2.getSelectedValue();
      actionListModel2.removeElement(act);
      if(eventList.getSelectedIndex()>-1){
        String sel=(String)eventList.getSelectedValue();
        eventRemoveAction(sel, act);
      }
    }
  }

  private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {
    int inx=eventList.getSelectedIndex();
    if(inx>-1){
      String sel=(String)eventList.getSelectedValue();
      eventListModel.removeElement(sel);
      eventTM.remove(sel);

      if(eventTM.size()>0){
        if(inx>eventTM.size()-1) inx=eventTM.size()-1;
        eventList.setSelectedIndex(inx);
      }
      showEvent();
    }
  }

  private void updateConditionBtnActionPerformed(java.awt.event.ActionEvent evt) {
    updateConditionItem();
  }
void updateConditionItem(){
        if(conditionList.getSelectedIndex()>-1){
     String sel=(String)conditionList.getSelectedValue();
       String data[]=new String[condMaxArrCnt];
       for(int i=0;i<data.length;i++) data[i]="";
       data[0]=sel;
       data[1]=(String)jComboBox14.getSelectedItem();
       data[22]=(String)jComboBox48.getSelectedItem();
       data[18]=(String)jComboBox46.getSelectedItem();
       data[19]=(String)jComboBox47.getSelectedItem();
       data[2]=(String)jComboBox16.getSelectedItem();
           data[3]=(String)jComboBox20.getSelectedItem();
           data[4]=(String)jComboBox26.getSelectedItem();

           data[5]=jTextField46.getText();
           data[6]=jTextField13.getText();
           if(jCheckBox35.isSelected()) data[7]="Y" ;
           else  data[7]="N";
           data[8]=jTextField4.getText();
           data[9]=jTextField12.getText();
           data[10]=(String)jComboBox27.getSelectedItem();
           data[11]=jTextField14.getText();
           if(jCheckBox30.isSelected()) data[12]="Y" ;
           else  data[12]="N";
           data[13]=jTextField55.getText();
           if(jCheckBox31.isSelected()) data[14]="Y" ;
           else  data[14]="N";
           if(jCheckBox32.isSelected()) data[15]="Y" ;
           else  data[15]="N";
           data[16]=jTextField65.getText();
           data[17]=jTextField66.getText();
           data[20]=(String)jComboBox29.getSelectedItem();
           data[21]=(String)jComboBox43.getSelectedItem();
        conditionTM.put(sel, ylib.arrayToCsvLine(data));
        updateExecEnv();
    }
}
  

 void updateExecEnv(){

}

  private void updateActionBtnActionPerformed(java.awt.event.ActionEvent evt) {
    updateActionItem();
  }
void updateActionItem(){
       if(actionList.getSelectedIndex()>-1){
     String sel=(String)actionList.getSelectedValue();
       String data[]=new String[actMaxArrCnt];
       for(int i=0;i<data.length;i++) data[i]="";
       data[0]=sel;
       data[1]=(String)jComboBox19.getSelectedItem();
       data[2]=(String)jComboBox18.getSelectedItem();

       data[3]=(String)jComboBox28.getSelectedItem();
       data[4]=(String)jComboBox29.getSelectedItem();
       data[5]=jTextField56.getText();
       data[6]=jTextField15.getText();
       if(jCheckBox34.isSelected()) data[7]="Y" ;
           else  data[7]="N";
       data[8]=jTextField16.getText();
       data[9]=jTextField21.getText();

       data[10]=(String)jComboBox30.getSelectedItem();
       data[11]=jTextField31.getText();
       if(jCheckBox8.isSelected()) data[12]="Y" ;
           else  data[12]="N";
       data[13]=(String)jComboBox31.getSelectedItem();
       if(jCheckBox11.isSelected()) data[14]="Y" ;
           else  data[14]="N";
       data[15]=jTextField32.getText();
       data[16]=(String)jComboBox44.getSelectedItem();
       data[17]=(String)jComboBox45.getSelectedItem();
       data[18]=jLabel39.getText();
       if(jCheckBox4.isSelected()) data[19]="Y" ;
       else data[19]="N";
       data[20]=jTextField27.getText();
       data[21]=jTextField28.getText();
       data[22]=jTextField29.getText();
       if(jCheckBox5.isSelected()) data[23]="Y" ;
           else  data[23]="N";
       data[24]=jTextField30.getText();
       if(jCheckBox7.isSelected()) data[25]="Y" ;
           else  data[25]="N";
       if(jCheckBox6.isSelected()) data[26]="Y" ;
           else  data[26]="N";
       if(jRadioButton10.isSelected()) data[27]="Y" ;
           else  data[27]="N";

       data[33]=jTextField63.getText();
       if(jCheckBox27.isSelected()) data[34]="Y" ;
           else  data[34]="N";
       data[35]=jTextField62.getText();
       if(data[35].length()>6 && data[35].indexOf(".class")==data[35].length()-6) data[35]=data[35].substring(0,data[35].indexOf(".class"));
       if(jCheckBox29.isSelected()) data[36]="Y" ;
           else  data[36]="N";
       data[37]=jTextField64.getText();
       if(jCheckBox28.isSelected()) data[38]="Y" ;
           else  data[38]="N";
       data[39]=(String)jComboBox49.getSelectedItem();
       data[43]=(String)jComboBox32.getSelectedItem();
       data[44]=(String)jComboBox41.getSelectedItem();
       data[45]=jTextField57.getText();
       if(jCheckBox33.isSelected()) data[46]="Y" ;
           else  data[46]="N";
       data[47]=jTextField58.getText();
       if(jCheckBox36.isSelected()) data[48]="Y" ;
           else  data[48]="N";
       data[49]=jTextField59.getText();
       data[50]=jTextField60.getText();

       if(jCheckBox41.isSelected()) data[55]="Y" ;
           else  data[55]="N";
       data[56]=jTextField70.getText();
       if(jCheckBox42.isSelected()) data[57]="Y" ;
           else  data[57]="N";
       data[71]=jTextField59.getText();
       data[72]=jTextField60.getText();

       if(jCheckBox39.isSelected()) data[65]="Y" ;
           else  data[65]="N";
       data[66]=jTextField67.getText();
       if(jCheckBox40.isSelected()) data[67]="Y" ;
           else  data[67]="N";
       data[68]=jTextField68.getText();
       data[69]=jTextField69.getText();
       data[70]=jTextField54.getText();
       actionTM.put(sel,ylib.arrayToCsvLine(data));
       chkAndAdjustEvents();
  }
}
  

  void chkAndAdjustEvents(){
    props.put("monitor-interval-h", "24");props.put("monitor-interval-m", "0");props.put("monitor-interval-s", "0");
    Iterator it=eventTM.keySet().iterator();
    boolean firstContCmd=true;
    long oldInterval=0;
    for(;it.hasNext();){
        boolean chkOK=true;
        String key=(String)it.next();
        String eventInfo[]=ylib.csvlinetoarray((String)eventTM.get(key));
         int cCnt=Integer.parseInt(eventInfo[1]);
        int aCnt=Integer.parseInt(eventInfo[2]);
        TreeMap actionCodeTM=new TreeMap();
        if(eventInfo.length>= (3+cCnt+aCnt)){
           for(int i=0;i<aCnt;i++){
             String aId=eventInfo[3+cCnt+i];
             String actionInfo[]=ylib.csvlinetoarray((String)actionTM.get(aId));
             if(actionInfo.length > 15 && actionInfo[2].equalsIgnoreCase("Send Command") && actionInfo[14].equalsIgnoreCase("Y") && isNumeric(actionInfo[15])){
            int interval=Integer.parseInt(actionInfo[15]);
            if(interval>0){
                if(!firstContCmd){
                  oldInterval=getPropsLong("monitor-interval-h")*60L*60L+getPropsLong("monitor-interval-m")*60L+getPropsLong("monitor-interval-s");
                  if(interval>=oldInterval) chkOK=false;
                }
                if(chkOK){
                int s=interval%60;
                props.put("monitor-interval-s", ""+s);
                interval=interval-s;
                int m=interval/60;
                m=interval%60;
                props.put("monitor-interval-m", ""+m);
                interval=interval-m;
                int h=interval/60;
                props.put("monitor-interval-h", ""+h);
                firstContCmd=false;
                }
            }
        }
      }
    }
  }
}
  private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
    if(eventListModel.size()<1) {newEvent(); eventList.setSelectedIndex(0);}
    if(eventList.getSelectedIndex()==-1) { JOptionPane.showMessageDialog(this, "Please select which event to add to."); return;}
    if(conditionList.getSelectedIndex()>-1){
      updateConditionItem();
      String ev=(String)eventList.getSelectedValue();
      String cond=(String)conditionList.getSelectedValue();
      int cnt=conditionList2.getModel().getSize();
      TreeMap tm=new TreeMap();

      for(int i=0;i<cnt;i++){
        String item=(String)conditionList2.getModel().getElementAt(i);

        tm.put(item, item);
      }
      if(!tm.containsKey(cond)){
        tm.put(cond,cond);
        

        eventSetCondition(ev,tm);
      }
    } else  { JOptionPane.showMessageDialog(this, "Please select which condition to be added."); return;}
  }

  private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {
    if(eventListModel.size()<1) {newEvent(); eventList.setSelectedIndex(0);}
    if(eventList.getSelectedIndex()==-1) { JOptionPane.showMessageDialog(this, "Please select which event to add to."); return;}
    if(actionList.getSelectedIndex()>-1){
      updateActionItem();
      String ev=(String)eventList.getSelectedValue();
      String act=(String)actionList.getSelectedValue();
      int cnt=actionList2.getModel().getSize();
      TreeMap tm=new TreeMap();

      for(int i=0;i<cnt;i++){
        String item=(String)actionList2.getModel().getElementAt(i);

        tm.put(item, item);
      }
      if(!tm.containsKey(act)){
        tm.put(act, act);
        

        eventSetAction(ev,tm);
      }
    } else  { JOptionPane.showMessageDialog(this, "Please select which action to be added."); return;}
  }

  private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {
    int cnt=chartList.getModel().getSize();
    int max=0;
    if(cnt>0){
      for(int i=0;i<cnt;i++) {
         String tm=(String)chartList.getModel().getElementAt(i);
         int v=Integer.parseInt(tm.substring(2));
         if(v>max) max=v;
      }
    }
    max=max+1;
    String newItem="CT"+zero(4-String.valueOf(max).length())+max;
    chartListModel.addElement(newItem);
    chartList.setSelectedValue(newItem, true);
    jTextField44.setText(newItem);
    jTextField47.setText("0");
    jTextField48.setText("0");
    jTextField49.setText("100");
    jTextField50.setText("100");
    jCheckBox13.setSelected(false);
    jCheckBox14.setSelected(false);
    jCheckBox15.setSelected(false);
    jCheckBox16.setSelected(false);
    jCheckBox19.setSelected(false);
    jCheckBox20.setSelected(false);
    jCheckBox21.setSelected(false);
    jCheckBox22.setSelected(false);
    jCheckBox23.setSelected(false);
    jCheckBox24.setSelected(false);
    chartTM.put(newItem, newItem+",");
  }

  private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {
    int cnt=curveList.getModel().getSize();
    int max=0;
    if(cnt>0){
      for(int i=0;i<cnt;i++) {
         String tm=(String)curveList.getModel().getElementAt(i);
         int v=Integer.parseInt(tm.substring(2));
         if(v>max) max=v;
      }
    }
    max=max+1;
    String newItem="CV"+zero(4-String.valueOf(max).length())+max;
    curveListModel.addElement(newItem);
    curveList.setSelectedValue(newItem, true);
    curveTM.put(newItem, newItem+",");
  }

  private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {
    if(chartList.getSelectedIndex()>-1){
      int selNo=chartList.getSelectedIndex();
      String sel=(String)chartList.getSelectedValue();
      chartListModel.removeElement(sel);
      chartTM.remove(sel);
      if(chartListModel.size() < selNo+1) selNo=chartListModel.size()-1;
      if(selNo>-1){
      chartList.setSelectedIndex(selNo);
      sel=(String)chartList.getSelectedValue();
      showChart();
      }
    }
  }

  private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {
    if(chartList.getSelectedIndex()>-1){
     String sel=(String)chartList.getSelectedValue();
      String data[]=new String[chartMaxArrCnt];
       for(int i=0;i<data.length;i++) data[i]="";
     if(chartTM.get(sel)!=null){
       String data2[]=ylib.csvlinetoarray((String)chartTM.get(sel));
       for(int i=0;i<data2.length && i<data.length;i++) data[i]=data2[i];
     }
     data[0]=sel;
       data[1]=String.valueOf(((Color)jComboBox35.getSelectedItem()).getRGB());
       data[2]=jTextField47.getText();
       data[3]=jTextField48.getText();
       data[4]=jTextField49.getText();
       data[5]=jTextField50.getText();
       data[6]=jTextField33.getText();
       data[7]=jTextField34.getText();
       if(jCheckBox12.isSelected()) data[8]="Y"; else data[8]="N";
       if(jCheckBox13.isSelected()) data[9]="Y"; else data[9]="N";
       if(jRadioButton2.isSelected()) data[10]="1"; else data[10]="2";
       data[11]=jTextField35.getText();
       data[12]=jTextField36.getText();
       data[13]=jTextField37.getText();
       data[14]=jTextField38.getText();
       if(jRadioButton4.isSelected()) data[15]="1"; else data[15]="2";
       if(jCheckBox14.isSelected()) data[16]="Y"; else data[16]="N";

       if(jCheckBox15.isSelected()) data[19]="Y"; else data[19]="N";
       if(jCheckBox16.isSelected()) data[20]="Y"; else data[20]="N";

       if(jCheckBox19.isSelected()) data[23]="Y"; else data[23]="N";
       if(jCheckBox20.isSelected()) data[24]="Y"; else data[24]="N";
       if(jCheckBox21.isSelected()) data[25]="Y"; else data[25]="N";
       if(jCheckBox22.isSelected()) data[26]="Y"; else data[26]="N";
       if(jCheckBox23.isSelected()) data[27]="Y"; else data[27]="N";
       data[28]=jTextField43.getText();
       data[29]=jTextField44.getText();
       if(jCheckBox24.isSelected()) data[30]="Y"; else data[30]="N";
       data[31]=(String)jComboBox42.getSelectedItem();
       if(jCheckBox26.isSelected()) data[32]="Y"; else data[32]="N";
       data[33]=jTextField52.getText();
       data[34]=jTextField51.getText();
       chartTM.put(sel, data);

     }
  }

  private void chartListMouseClicked(java.awt.event.MouseEvent evt) {
   showChart();
  }

  private void curveListMouseClicked(java.awt.event.MouseEvent evt) {
    showCurve();
  }

  private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {
    if(curveList.getSelectedIndex()>-1){
     String sel=(String)curveList.getSelectedValue();
      String data[]=new String[curveMaxArrCnt];
       for(int i=0;i<data.length;i++) data[i]="";
     if(curveTM.get(sel)!=null){
       String data2[]=ylib.csvlinetoarray((String)curveTM.get(sel));
       for(int i=0;i<data2.length && i<data.length;i++) data[i]=data2[i];
     }
     data[0]=sel;
       data[1]="";
       data[2]="";
       data[3]=(String)jComboBox34.getSelectedItem();
       data[4]=(String)jComboBox40.getSelectedItem();
       data[5]=(String)jComboBox51.getSelectedItem();
       data[6]=(String)jComboBox52.getSelectedItem();
       data[7]=jLabel91.getText();
       data[8]=(String)jComboBox33.getSelectedItem();
       data[9]=String.valueOf(((Color)jComboBox38.getSelectedItem()).getRGB());
       data[10]=(String)jComboBox50.getSelectedItem();
       curveTM.put(sel, data);
     }
  }

  private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {
    if(curveList.getSelectedIndex()>-1){
      int selNo=curveList.getSelectedIndex();
      String sel=(String)curveList.getSelectedValue();
      curveListModel.removeElement(sel);
      curveTM.remove(sel);
      if(curveListModel.size() < selNo+1) selNo=curveListModel.size()-1;
      if(selNo>-1){
      curveList.setSelectedIndex(selNo);
      sel=(String)curveList.getSelectedValue();
      showCurve();
      }
    }
  }

  private void clearShowBtnActionPerformed(java.awt.event.ActionEvent evt) {
  if(currentViewDSrc.length()>0) {
    if(dSrcRecord.get(currentViewDSrc)!=null) {
      WSNDataRecord dRecord=(WSNDataRecord) dSrcRecord.get(currentViewDSrc);
      dRecord.clear();
      dSrcRecord.put(currentViewDSrc, dRecord);
    }
  }
  else if(saveFileCB.isSelected()) fileThread.setData(0,"0","0", receiveTP.getText().trim());

  clear();
  }

  private void receiveListMouseClicked(java.awt.event.MouseEvent evt) {
  changeReceiveListItem();

  }

  private void receiveListKeyReleased(java.awt.event.KeyEvent evt) {
if(evt.getKeyCode()==38 || evt.getKeyCode()==40 )  changeReceiveListItem();
  }

  private void clearSendBtnActionPerformed(java.awt.event.ActionEvent evt) {
    sendTA.setText("");
  }

  private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {
		String cmd=sendTA.getText();
                Vector sendId=new Vector();
		if(cmd.trim().length()<1) {JOptionPane.showMessageDialog(this,"No command or data to be sent"); return;}
                String interval=sendIntervalTF.getText().trim();
                if(continueSendCB.isSelected()) {
                  if(interval.length()>0 && isNumeric(interval) && Double.parseDouble(interval)>0) {}
                   else {JOptionPane.showMessageDialog(this,"No time interval for \"continue send\""); return;}
                  stopContinueSendBtn.setEnabled(true);
                }
                if(interval==null || interval.length()<1) interval="3600";
                String selected=(String)sendList.getSelectedValue();
                if(selected!=null){
                String id=getItemId(selected);
                int inx=selected.indexOf(":");
                String id2=selected;
                if(inx>-1) id2=selected.substring(inx+1);

                cmd=wn.w.e642(cmd);
                if(selected.equals(allItemsName)){
                    int count=sendList.getModel().getSize();
                    for(int i=1;i<count;i++){
                      if(!getItemId((String)sendListModel.getElementAt(i)).equals("0"))  sendId.add(getItemId((String)sendListModel.getElementAt(i)));
                    }
                    Enumeration en=sendId.elements();
                    for(;en.hasMoreElements();){
                       cmd="performcommand wsn.WSN cmd all all "+send16RB.isSelected()+" "+chkSumCB.isSelected()+" "+continueSendCB.isSelected()+" "+interval+" "+cmd+" "+wn.w.e642((String)chkSumCBB.getSelectedItem())+" 0 0 0 0 0"; 
                       wn.w.sendToOne(cmd,(String)en.nextElement());
                    }
                } else if(id!=null){

   		   cmd="performcommand wsn.WSN cmd "+id2+" 1 "+send16RB.isSelected()+" "+chkSumCB.isSelected()+" "+continueSendCB.isSelected()+" "+interval+" "+cmd+" "+wn.w.e642((String)chkSumCBB.getSelectedItem())+" 0 0 0 0 0"; 
                    wn.w.sendToOne(cmd,id);
                } else if(id==null) {JOptionPane.showMessageDialog(this, "Yet not connect, cannot send out any message."); return;}
                stopContinueSendBtn.setEnabled(true);
                }
  }

public String getDataSrcFromStation(String station){
  String src="";
  if(station!=null) src=(String)stations.get(station);
  return src;
}
  private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox20.getSelectedIndex()==0){
        jComboBox26.setVisible(false);
        jLabel30.setVisible(false);
        jTextField46.setVisible(false);
        jLabel93.setVisible(false);
        jTextField13.setVisible(false);
        jCheckBox35.setText("Byte from");
      } else {
        jComboBox26.setVisible(true);
        jLabel30.setVisible(true);
        jTextField46.setVisible(true);
        jLabel93.setVisible(true);
        jTextField13.setVisible(true);
        jCheckBox35.setText("Char from");
        String sel=(String)jComboBox26.getSelectedItem();
        if(sel.equalsIgnoreCase("whole line")){
        jLabel30.setVisible(false);
        jTextField46.setVisible(false);
        jLabel93.setVisible(false);
        jTextField13.setVisible(false);
        }
      }
    }
  }

  private void jComboBox28ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox28.getSelectedIndex()==0){
        jCheckBox27.setVisible(true);
        jComboBox29.setVisible(false);
        jLabel79.setVisible(false);
        jTextField56.setVisible(false);

        jTextField53.setVisible(false);
        jLabel96.setVisible(false);
        jTextField15.setVisible(false);
        jCheckBox34.setText("Byte from");
      } else {
        jCheckBox27.setVisible(false);
        jComboBox29.setVisible(true);
        jLabel79.setVisible(true);
        jTextField56.setVisible(true);

        jTextField53.setVisible(true);
        jLabel96.setVisible(true);
        jTextField15.setVisible(true);
        jCheckBox34.setText("Char from");
        String sel=(String)jComboBox29.getSelectedItem();
        if(sel.equalsIgnoreCase("whole line")){
        jLabel79.setVisible(false);
        jTextField56.setVisible(false);

        jTextField53.setVisible(false);
        jLabel96.setVisible(false);
        jTextField15.setVisible(false);
        }
      }
    }

  }

  private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=eventList.getSelectedIndex();
    if(sel>0){
      String key=(String)eventList.getSelectedValue();
      String data=(String)eventTM.get(key);
      eventList.setSelectedIndex(sel-1);
      String key2=(String)eventList.getSelectedValue();
      String data2=(String)eventTM.get(key);
      eventTM.put(key, data2);
      eventTM.put(key2, data);
   }
  }

  private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=conditionList.getSelectedIndex();
    if(sel>0){
      String key=(String)conditionList.getSelectedValue();
      String data=(String)conditionTM.get(key);
      conditionList.setSelectedIndex(sel-1);
      String key2=(String)conditionList.getSelectedValue();
      String data2=(String)conditionTM.get(key2);
      conditionTM.put(key, data2);
      conditionTM.put(key2, data);
      eventSwapItem(key,key2);
   }
  }

  private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=actionList.getSelectedIndex();
    if(sel>0){
      String key=(String)actionList.getSelectedValue();
      String data=(String)actionTM.get(key);
      actionList.setSelectedIndex(sel-1);
      String key2=(String)actionList.getSelectedValue();
      String data2=(String)actionTM.get(key2);
      actionTM.put(key, data2);
      actionTM.put(key2, data);
      eventSwapItem(key,key2);
   }
  }

  private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=eventList.getSelectedIndex();
    if(sel>-1 && sel<(eventListModel.size()-1)){
      String key=(String)eventList.getSelectedValue();
      String data=(String)eventTM.get(key);
      eventList.setSelectedIndex(sel+1);
      String key2=(String)eventList.getSelectedValue();
      String data2=(String)eventTM.get(key);
      eventTM.put(key, data2);
      eventTM.put(key2, data);
   }
  }

  private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=conditionList.getSelectedIndex();
    if(sel>-1 && sel<(conditionListModel.size()-1)){
      String key=(String)conditionList.getSelectedValue();
      String data=(String)conditionTM.get(key);
      conditionList.setSelectedIndex(sel+1);
      String key2=(String)conditionList.getSelectedValue();
      String data2=(String)conditionTM.get(key2);
      conditionTM.put(key, data2);
      conditionTM.put(key2, data);
      eventSwapItem(key,key2);
   }
  }

  private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=actionList.getSelectedIndex();
    if(sel>-1 && sel<(actionListModel.size()-1)){
      String key=(String)actionList.getSelectedValue();
      String data=(String)actionTM.get(key);
      actionList.setSelectedIndex(sel+1);
      String key2=(String)actionList.getSelectedValue();
      String data2=(String)actionTM.get(key2);
      actionTM.put(key, data2);
      actionTM.put(key2, data);
      eventSwapItem(key,key2);
   }
  }
void eventSwapItem(String item1,String item2){
  Iterator it=eventTM.keySet().iterator();
  String tmpItem="tmpItem";
  for(;it.hasNext();){
    String key=(String)it.toString();
    String items=(String)eventTM.get(key);
    items=ylib.replace(items, item1, tmpItem);
    items=ylib.replace(items, item2, item1);
    items=ylib.replace(items, tmpItem, item2);
  }
  showEvent();
}
  private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=chartList.getSelectedIndex();
    if(sel>0){
      String key=(String)chartList.getSelectedValue();
      String data=(String)chartTM.get(key);
      chartList.setSelectedIndex(sel-1);
      String key2=(String)chartList.getSelectedValue();
      String data2=(String)chartTM.get(key2);
      chartTM.put(key, data2);
      chartTM.put(key2, data);
   }

  }

  private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=curveList.getSelectedIndex();
    if(sel>0){
      String key=(String)curveList.getSelectedValue();
      String data=(String)curveTM.get(key);
      curveList.setSelectedIndex(sel-1);
      String key2=(String)curveList.getSelectedValue();
      String data2=(String)curveTM.get(key2);
      curveTM.put(key, data2);
      curveTM.put(key2, data);
   }
  }

  private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=chartList.getSelectedIndex();
    if(sel>-1 && sel<(chartListModel.size()-1)){
      String key=(String)chartList.getSelectedValue();
      String data=(String)chartTM.get(key);
      chartList.setSelectedIndex(sel+1);
      String key2=(String)chartList.getSelectedValue();
      String data2=(String)chartTM.get(key2);
      chartTM.put(key, data2);
      chartTM.put(key2, data);
   }
  }

  private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {
    int sel=curveList.getSelectedIndex();
    if(sel>-1 && sel<(curveListModel.size()-1)){
      String key=(String)curveList.getSelectedValue();
      String data=(String)curveTM.get(key);
      curveList.setSelectedIndex(sel+1);
      String key2=(String)curveList.getSelectedValue();
      String data2=(String)curveTM.get(key2);
      curveTM.put(key, data2);
      curveTM.put(key2, data);
   }
  }

  private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox14.getSelectedIndex()>0){
        String sel=(String)jComboBox14.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox48.removeAllItems();
        jComboBox48.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(sel)){
            boolean exists = false;
            for (int index = 0; index < jComboBox48.getItemCount() && !exists; index++) {
               if (info[1].equals((String)jComboBox48.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox48.addItem(info[1]);
             }
          }
        }
        jComboBox48.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox19ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox19.getSelectedIndex()>0){
        String sel=(String)jComboBox19.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox49.removeAllItems();
        jComboBox49.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox49.getItemCount() && !exists; index++) {
               if (info[1].equals((String)jComboBox49.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox49.addItem(info[1]);
             }
          }
        }
        jComboBox49.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox48ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox48.getSelectedIndex()>0){
        String station=(String)jComboBox14.getSelectedItem();
        String sel=(String)jComboBox48.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox46.removeAllItems();
        jComboBox46.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox46.getItemCount() && !exists; index++) {
               if (info[2].equals((String)jComboBox46.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox46.addItem(info[2]);
             }
          }
        }
        jComboBox46.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox49ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox49.getSelectedIndex()>0){
        String station=(String)jComboBox19.getSelectedItem();
        String sel=(String)jComboBox49.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox44.removeAllItems();
        jComboBox44.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox44.getItemCount() && !exists; index++) {
               if (info[2].equals((String)jComboBox44.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox44.addItem(info[2]);
             }
          }
        }
        jComboBox44.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox46ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox46.getSelectedIndex()>0){
        String station=(String)jComboBox14.getSelectedItem();
        String device=(String)jComboBox48.getSelectedItem();
        String sel=(String)jComboBox46.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox47.removeAllItems();
        jComboBox47.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(device) && info[2].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox47.getItemCount() && !exists; index++) {
               if (info[4].equals((String)jComboBox47.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox47.addItem(info[4]);
             }
          }
        }
        jComboBox47.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox44ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox44.getSelectedIndex()>0){
        String station=(String)jComboBox19.getSelectedItem();
        String device=(String)jComboBox49.getSelectedItem();
        String sel=(String)jComboBox44.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox45.removeAllItems();
        jComboBox45.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(device) && info[2].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox45.getItemCount() && !exists; index++) {
               if (info[4].equals((String)jComboBox45.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox45.addItem(info[4]);
             }
          }
        }
        jComboBox45.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox45ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox45.getSelectedIndex()>0){
        String station=(String)jComboBox19.getSelectedItem();
        String device=(String)jComboBox49.getSelectedItem();
        String model=(String)jComboBox44.getSelectedItem();
        String sel=(String)jComboBox45.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jLabel39.setText("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(device) && info[2].equalsIgnoreCase(model) && info[4].equalsIgnoreCase(sel)){
            jLabel39.setText(info[9]);
            break;
          }
        }
      } 
    }
  }

  private void jComboBox26ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      String sel=(String)jComboBox26.getSelectedItem();
      if(sel.equalsIgnoreCase("Whole line")){
        jLabel130.setVisible(false);
        jTextField46.setVisible(false);
        jLabel93.setVisible(false);
        jTextField13.setVisible(false);
      } else if(sel.equalsIgnoreCase("Fixed column length")){
        jLabel130.setVisible(true);
        jTextField46.setVisible(true);
        jLabel93.setVisible(true);
        jTextField13.setVisible(true);
      } else {
        jLabel130.setVisible(false);
        jTextField46.setVisible(false);
        jLabel93.setVisible(true);
        jTextField13.setVisible(true);
      }
      }
  }

  private void jComboBox29ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      String sel=(String)jComboBox29.getSelectedItem();
      if(sel.equalsIgnoreCase("Whole line")){
        jLabel79.setVisible(false);
        jTextField56.setVisible(false);
        jLabel96.setVisible(false);
        jTextField15.setVisible(false);
      } else if(sel.equalsIgnoreCase("Fixed column length")){
        jLabel79.setVisible(true);
        jTextField56.setVisible(true);
        jLabel96.setVisible(true);
        jTextField15.setVisible(true);
      } else {
        jLabel79.setVisible(false);
        jTextField56.setVisible(false);
        jLabel96.setVisible(true);
        jTextField15.setVisible(true);
      }
    }
  }

  private void jComboBox40ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox40.getSelectedIndex()>0){
        String sel=(String)jComboBox40.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox50.removeAllItems();
        jComboBox50.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(sel)){
            boolean exists = false;
            for (int index = 0; index < jComboBox50.getItemCount() && !exists; index++) {
               if (info[1].equals((String)jComboBox50.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox50.addItem(info[1]);
             }
          }
        }
        jComboBox50.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox50ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox50.getSelectedIndex()>0){
        String station=(String)jComboBox40.getSelectedItem();
        String sel=(String)jComboBox50.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox51.removeAllItems();
        jComboBox51.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox51.getItemCount() && !exists; index++) {
               if (info[2].equals((String)jComboBox51.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox51.addItem(info[2]);
             }
          }
        }
        jComboBox51.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox51ItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      if(jComboBox51.getSelectedIndex()>0){
        String station=(String)jComboBox40.getSelectedItem();
        String device=(String)jComboBox50.getSelectedItem();
        String sel=(String)jComboBox51.getSelectedItem();
        Iterator it=sensors.values().iterator();
        jComboBox52.removeAllItems();
        jComboBox52.addItem("");
        for(;it.hasNext();){
          String info[]=ylib.csvlinetoarray((String)it.next());
          if(info[0].equalsIgnoreCase(station) && info[1].equalsIgnoreCase(device) && info[2].equalsIgnoreCase(sel)){
            boolean exists=false;
            for (int index = 0; index < jComboBox52.getItemCount() && !exists; index++) {
               if (info[4].equals((String)jComboBox52.getItemAt(index))) {
                  exists = true; break;
               }
             }
	     if(!exists) {
               jComboBox52.addItem(info[4]);
             }
          }
        }
        jComboBox52.setSelectedIndex(0);
      } 
    }
  }

  private void jComboBox40ActionPerformed(java.awt.event.ActionEvent evt) {

  }

  private void byStationActionPerformed(java.awt.event.ActionEvent evt) {
   updateChartProfile();
  }

  private void byDeviceActionPerformed(java.awt.event.ActionEvent evt) {
   updateChartProfile();
  }

  private void byModelActionPerformed(java.awt.event.ActionEvent evt) {
   updateChartProfile();
  }

  private void byChartGroupActionPerformed(java.awt.event.ActionEvent evt) {
   updateChartProfile();
  }

  private void chartGroupCBItemStateChanged(java.awt.event.ItemEvent evt) {
    if(evt.getStateChange()==evt.SELECTED){
      updateChartProfile();
    }
  }

  private void btnEditSrcActionPerformed(java.awt.event.ActionEvent evt) {
  

    if(!adminLogin && !userLogin){
     if(userDialog==null) userDialog=new UserDialog(this,true);
      userDialog.setVisible(true);
   }
  if(adminLogin || userLogin){
    if(editFrame!=null && editFrame.isVisible()){
        editFrame.requestFocusInWindow();
        return;
    }
  } else return;

    if(editFrame==null) editFrame=new CIEditFrame(this);

    editFrame.setContent();
    editFrame.setVisible(true);
  }

  private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
      if(confirmExit()) setVisible(false);
  }

  private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
    if (getPropsString("record-directory").length() < 1) {
      props.setProperty("record-directory", System.getProperty("user.dir"));
    }
    File file = new File(getPropsString("record-directory"));
    if(!file.exists()) {
        if(!file.mkdir()){
            props.setProperty("record-directory", System.getProperty("user.dir"));
            file = new File(getPropsString("record-directory"));
        }
    }
    CIDT.open(file);
  }

  private void fileMenuItem01ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",35);
  }

  private void fileMenuItem02ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",36);
  }

  private void fileMenuItem03ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",37);
  }

  private void fileMenuItem04ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",38);
  }

  private void fileMenuItem05ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",39);
  }

  private void toolMenuItem01ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",40);
  }

  private void toolMenuItem02ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",41);
  }

  private void toolMenuItem03ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",42);
  }

  private void toolMenuItem04ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",43);
  }

  private void toolMenuItem05ActionPerformed(java.awt.event.ActionEvent evt) {
     eventThread.setStatus(wn.w.getGNS(1),"",44);
  }

  private void helpMenuItem01ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",45);
  }

  private void helpMenuItem02ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",46);
  }

  private void helpMenuItem03ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",47);
  }

  private void helpMenuItem04ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",48);
  }

  private void helpMenuItem05ActionPerformed(java.awt.event.ActionEvent evt) {
      eventThread.setStatus(wn.w.getGNS(1),"",49);
  }

  private void button04ActionPerformed(java.awt.event.ActionEvent evt) {
 eventThread.setStatus(wn.w.getGNS(1),"",28);
  }

  private void button05ActionPerformed(java.awt.event.ActionEvent evt) {
eventThread.setStatus(wn.w.getGNS(1),"",29);
  }

  private void button06ActionPerformed(java.awt.event.ActionEvent evt) {
eventThread.setStatus(wn.w.getGNS(1),"",30);
  }

  private void button07ActionPerformed(java.awt.event.ActionEvent evt) {
eventThread.setStatus(wn.w.getGNS(1),"",31);
  }

  private void button08ActionPerformed(java.awt.event.ActionEvent evt) {
eventThread.setStatus(wn.w.getGNS(1),"",32);
  }

  private void button09ActionPerformed(java.awt.event.ActionEvent evt) {
eventThread.setStatus(wn.w.getGNS(1),"",33);
  }

  private void button10ActionPerformed(java.awt.event.ActionEvent evt) {
eventThread.setStatus(wn.w.getGNS(1),"",34);
  }

  private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {
         String webAddr="http://www.cloud-rain.com/web/ci/doc/"+bundle2.getString("CrInstrument.xy.msg2");
         if(webAddr.indexOf("http")==-1){
             webAddr=webAddr.substring(5);
             webAddr=webAddr.replace('/', File.separatorChar);
             webAddr=(new File(webAddr)).getAbsolutePath();
             webAddr="file:///"+webAddr.replace(File.separatorChar,'/');
         }

         openURL.open(webAddr);
  }

  private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
    if(!adminLogin){
      if (mgntDialog == null) {
        mgntDialog = new MgntDialog(this, true);
      }
      mgntDialog.setVisible(true);
    }
  }

  private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
    if (!adminLogin && !userLogin) {
      if (userDialog == null) {
        userDialog = new UserDialog(this, true);
      }
      userDialog.setVisible(true);
    }
  }

  private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
    if (about == null) {
      about = new CIAbout(this, true);
    }
    about.setVisible(true);
  }

  private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {
   currentUI.clear();
   Iterator it=defaultUI.keySet().iterator();
   for(;it.hasNext();){
       String key=(String)it.next();
       currentUI.put(key, (String)defaultUI.get(key));
   }
   framePanel.setItems(currentUI);
  }

  private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {
    TreeMap tm=framePanel.getItems();
    Iterator it=tm.keySet().iterator();
    for(;it.hasNext();){
        String key=(String)it.next();
        if(tm.get(key)!=null) currentUI.put(key, (String)tm.get(key));
    }
    validate();
  }
private void updateItem(){

}
  private void button03ActionPerformed(java.awt.event.ActionEvent evt) {
    eventThread.setStatus(wn.w.getGNS(1),"",27);
  }

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {
  openURL.open("https://groups.google.com/d/forum/cr-instrument");
    }

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {
     openURL.open("https://github.com/h43832/cr-instrument");
    }

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {
       openURL.open(bundle2.getString("CrInstrument.xy.msg26"));
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
       if(optionsDialog==null){
           optionsDialog=new CIOptions(this,true);
       }
       optionsDialog.setVisible(true);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        int s[]=jTable1.getSelectedRows();
        for(int i=s.length-1;i>-1;i--) {
            ((DefaultTableModel)jTable1.getModel()).removeRow(s[i]);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
       ((DefaultTableModel)jTable1.getModel()).addRow(new Object[jTable1.getModel().getColumnCount()]);
       int rowN=jTable1.getRowCount()-1;
       for(int i=0;i>jTable1.getModel().getColumnCount();i++) jTable1.getModel().setValueAt("", rowN, i);
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
       updateSensorTable();
    }

    private void stopContinueSendBtnActionPerformed(java.awt.event.ActionEvent evt) {

		String cmd="";
                Vector sendId=new Vector();
                String selected=(String)sendList.getSelectedValue();
                if(selected!=null){
                String id=getItemId(selected);
                int inx=selected.indexOf(":");
                String id2=selected;
                if(inx>-1) id2=selected.substring(inx+1);
                if(selected.equals(allItemsName)){
                    int count=sendList.getModel().getSize();
                    for(int i=1;i<count;i++){
                        sendId.add(getItemId((String)sendListModel.getElementAt(i)));
                    }
                    Enumeration en=sendId.elements();
                    for(;en.hasMoreElements();){
                       cmd=cmd="performcommand wsn.WSN stopcontinue all all null"; 
                       wn.w.sendToOne(cmd,(String)en.nextElement());
                    }
                } else {
   		   cmd=cmd="performcommand wsn.WSN stopcontinue "+id2+" all null"; 
                    wn.w.sendToOne(cmd,id);
                }
                stopContinueSendBtn.setEnabled(false);
                }
    }

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {
    ((DefaultTableModel) jTable5.getModel()).addRow(new Object[jTable5.getModel().getColumnCount()]);
    }

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {
    ((DefaultTableModel) jTable6.getModel()).addRow(new Object[jTable6.getModel().getColumnCount()]);
    }

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
    int[] rows = jTable5.getSelectedRows();

    for (int i = rows.length - 1; i > -1; i--) {
      model.removeRow(rows[i]);
    }
    }

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {
         DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
    int[] rows = jTable5.getSelectedRows();

    for (int i = rows.length - 1; i > -1; i--) {
      model.removeRow(rows[i]);
    }
    }
private void changeReceiveListItem(){
  String datasrc=(String)receiveList.getSelectedValue();

  String lastDSrc=currentViewDSrc;
  this.currentViewDSrc=datasrc;
                if(!lastDSrc.equals(currentViewDSrc)){
                  if(lastDSrc.length()==0){
                    if(saveFileCB.isSelected()) fileThread.setData(0,"0","0", receiveTP.getText().trim());
                  }
                  clear();
                }
                WSNDataRecord dRecord =(WSNDataRecord)dSrcRecord.get(currentViewDSrc);
                if(dRecord!=null) textPaneAppend(dRecord.sb.toString());
}
  void clear(){
 try{

   styleDoc.remove(0, receiveTP.getDocument().getLength());
   beginTextPane=true;
 } catch(BadLocationException e){
   e.printStackTrace();
 }
 lastIsData=false;
}
  void eventRemoveCondition(String cond){
  Iterator it=eventTM.keySet().iterator();
  for(;it.hasNext();){
    String key=(String)it.next();
    eventRemoveCondition(key,cond);
  }
}

void eventAddCondition(String key,String cond){
    TreeMap conditionTM=new TreeMap();
    if(eventTM.get(key)!=null){
      String data2[]=ylib.csvlinetoarray((String)eventTM.get(key));
      int cnt=Integer.parseInt(data2[1]);
      for(int i=3;i<data2.length && i<cnt;i++) conditionTM.put(data2[i], data2[i]);
    } else conditionTM.put(cond, cond);
    eventSetCondition(key,conditionTM);
}

void eventRemoveCondition(String key,String cond){
    if(eventTM.get(key)!=null){
    String data[]=ylib.csvlinetoarray((String)eventTM.get(key));
      if(data.length>2) {
        int cnt=Integer.parseInt(data[1]);
        for(int i=0;i<cnt;i++){
          if(data.length>3+i && data[3+i].equals(cond)){
            String data2[]=new String[data.length-1];
            data2[0]=data[0];
            data2[1]=""+(cnt-1);
            data2[2]=data[2];
            for(int j=0;j<i;j++) data2[3+j]=data[3+j];
            for(int j=4+i;j<data.length;j++) data2[j-1]=data[j];
            eventTM.put(key, ylib.arrayToCsvLine(data2));
            break;
          }
        }
      }
    }
}
void eventRemoveAction(String act){
  Iterator it=eventTM.keySet().iterator();
  for(;it.hasNext();){
    String key=(String)it.next();
     eventRemoveAction(key, act);
  }
}

void eventAddAction(String key,String act){
    TreeMap actionTM=new TreeMap();
    if(eventTM.get(key)!=null){
      String data2[]=ylib.csvlinetoarray((String)eventTM.get(key));
      int cnt=Integer.parseInt(data2[1]);
      int cnt2=Integer.parseInt(data2[2]);
      for(int i=3+cnt;i<data2.length && i<cnt2;i++) actionTM.put(data2[i], data2[i]);
    } else actionTM.put(act, act);
    eventSetAction(key,actionTM);
}

void eventSetCondition(String key,TreeMap tm){
    String data[]=new String[eventMaxArrCnt];
    int cnt=0,cnt2=0;
    TreeMap actionTM=new TreeMap();
    for(int i=0;i<data.length;i++) data[i]="";
    data[0]=key;
    data[2]="0";
    if(eventTM.get(key)!=null){
      String data2[]=ylib.csvlinetoarray((String)eventTM.get(key));
      cnt=Integer.parseInt(data2[1]);
      cnt2=Integer.parseInt(data2[2]);
      for(int i=cnt+3,j=0;j<cnt2 && i<data.length && i<data2.length;i++) actionTM.put(data2[i], data2[i]);
    } else {
      cnt=0;
    }
    data[1]=""+tm.size();
    data[2]=""+actionTM.size();
    cnt=tm.size();
    Iterator it=tm.keySet().iterator();
    int inx=0;
    for(;it.hasNext();){
       data[3+inx]=(String)it.next();
       inx++;   
    }
    it=actionTM.keySet().iterator();
    inx=0;
    for(;it.hasNext();){
       data[3+cnt+inx]=(String)it.next();
       inx++;   
    }
    eventTM.put(key, ylib.arrayToCsvLine(data));
    showEvent();
}

void eventSetAction(String key,TreeMap tm){
    String data[]=new String[eventMaxArrCnt];
    int cnt=0,cnt2=0;
    for(int i=0;i<data.length;i++) data[i]="";
    data[0]=key;
    data[1]="0";
    if(eventTM.get(key)!=null){
      String data2[]=ylib.csvlinetoarray((String)eventTM.get(key));
      cnt=Integer.parseInt(data2[1]);
      for(int i=0;i<cnt+3 && i<data.length && i<data2.length;i++) data[i]=data2[i];
      for(int i=cnt+3;i<cnt+3 && i<data.length && i<data2.length;i++) data[i]="";
    } else {
      cnt=0;
    }
    data[2]=""+tm.size();
    cnt2=tm.size();
    Iterator it=tm.keySet().iterator();
    int inx=0;
    for(;it.hasNext();){
       data[3+cnt+inx]=(String)it.next();
       inx++;   
    }
    eventTM.put(key, ylib.arrayToCsvLine(data));
    showEvent();
}
void eventRemoveAction(String key,String act){
    if(eventTM.get(key)!=null){
    String data[]=ylib.csvlinetoarray((String)eventTM.get(key));
      if(data.length>2) {
        int cnt=Integer.parseInt(data[1]);
        int cnt2=Integer.parseInt(data[2]);
        for(int i=0;i<cnt2;i++){
          if(data.length>3+cnt+i && data[3+cnt+i].equals(act)){
            String data2[]=new String[data.length-1];
            data2[0]=data[0];
            data2[1]=data[1];
            data2[2]=""+(cnt2-1);
            for(int j=0;j<cnt;j++) data2[3+j]=data[3+j];
            for(int j=0;j<i;j++) data2[3+cnt+j]=data[3+cnt+j];
            for(int j=4+cnt+i;j<data.length;j++) data2[j-1]=data[j];
            eventTM.put(key, ylib.arrayToCsvLine(data2));
            break;
          }
        }
      }
    }
}
  void showCondition2(){
  if(conditionList2.getSelectedIndex()>-1){
     String sel=(String)conditionList2.getSelectedValue();
     conditionList.setSelectedValue(sel, true);
     showCondition1();
  }
}
void showCondition1(){
    if(conditionList.getSelectedIndex()>-1){
     String sel=(String)conditionList.getSelectedValue();
     if(conditionTM.get(sel)!=null){
       String data[]=ylib.csvlinetoarray((String)conditionTM.get(sel));
       if(data.length>1) jComboBox14.setSelectedItem(data[1]);
       if(data.length>22) jComboBox48.setSelectedItem(data[22]);
       if(data.length>18) jComboBox46.setSelectedItem(data[18]);
       if(data.length>19) jComboBox47.setSelectedItem(data[19]);
       if(data.length>2) {
         jComboBox16.setSelectedItem(data[2]);
         if(data[2].equalsIgnoreCase("Data condition")) {
           jPanel72.setVisible(true);
           if(data.length>3) jComboBox20.setSelectedItem(data[3]);
           if(data.length>4) {
             jComboBox26.setSelectedItem(data[4]);
             if(data.length>5 && data[4].equalsIgnoreCase("Fixed column length")) {
               jTextField46.setText(data[5]);
               jLabel130.setVisible(true);
               jTextField46.setVisible(true);
             } else {
               jLabel130.setVisible(true);
               jTextField46.setVisible(true);
             } 
           }
           if(data.length>4 && !data[4].equalsIgnoreCase("Whole line")){
               jLabel93.setVisible(true);
               jTextField13.setVisible(true);
               if(data.length>6) jTextField13.setText(data[6]);
           } else {
               jLabel93.setVisible(false);
               jTextField13.setVisible(false);
           }
           if(data.length>7 && wn.w.chkValue(data[7])) jCheckBox35.setSelected(true);
           else  jCheckBox35.setSelected(false);
           if(data.length>8) jTextField4.setText(data[8]);
           if(data.length>9) jTextField12.setText(data[9]);
           if(data.length>10) jComboBox27.setSelectedItem(data[10]);
           if(data.length>11) jTextField14.setText(data[11]);
           if(data.length>12 && wn.w.chkValue(data[12])) jCheckBox30.setSelected(true);
           else  jCheckBox30.setSelected(false);
            if(data.length>13) jTextField55.setText(data[13]);
           if(data.length>14 && wn.w.chkValue(data[14])) jCheckBox31.setSelected(true);
           else  jCheckBox31.setSelected(false);
           if(data.length>15 && wn.w.chkValue(data[15])) jCheckBox32.setSelected(true);
           else  jCheckBox32.setSelected(false);
           if(data.length>16) jTextField65.setText(data[16]);
           if(data.length>17) jTextField66.setText(data[17]);
           if(data.length>20) jComboBox39.setSelectedItem(data[20]);
           if(data.length>21) jComboBox43.setSelectedItem(data[21]);
           showConditionItem(data[2]);
         }
         else jPanel72.setVisible(false);
       }
     }
  }
}
void showAction2(){
  if(actionList2.getSelectedIndex()>-1){
     String sel=(String)actionList2.getSelectedValue();
     actionList.setSelectedValue(sel, true);
     showAction1();
  }
}
void showAction1(){
    if(actionList.getSelectedIndex()>-1){
     String sel=(String)actionList.getSelectedValue();
     if(actionTM.get(sel)!=null){
       String data[]=ylib.csvlinetoarray((String)actionTM.get(sel));
       if(data.length>1) jComboBox19.setSelectedItem(data[1]);
       if(data.length>39) jComboBox49.setSelectedItem(data[39]);
       if(data.length>16) jComboBox44.setSelectedItem(data[16]);
       if(data.length>17) jComboBox45.setSelectedItem(data[17]);
       if(data.length>2) {
         jComboBox18.setSelectedItem(data[2]);
         if(data[2].equalsIgnoreCase("Set data value")) {
           jPanel79.setVisible(true);
           if(data.length>3) jComboBox28.setSelectedItem(data[3]);
           if(data.length>4) {
             jComboBox29.setSelectedItem(data[4]);
             if(data.length>5 && data[4].equalsIgnoreCase("Fixed column length")) {
               jTextField56.setText(data[5]);
               jLabel79.setVisible(true);
               jTextField56.setVisible(true);
             } else {
               jLabel79.setVisible(false);
               jTextField56.setVisible(false);
             } 
           }
           if(data.length>4 && !data[4].equalsIgnoreCase("Whole line")){
               jLabel96.setVisible(true);
               jTextField15.setVisible(true);
               if(data.length>6) jTextField15.setText(data[6]);
           } else {
               jLabel96.setVisible(false);
               jTextField15.setVisible(false);
           }
           if(data.length>7 && wn.w.chkValue(data[7])) jCheckBox34.setSelected(true);
           else  jCheckBox34.setSelected(false);
           if(data.length>8) jTextField16.setText(data[8]);
           if(data.length>9) jTextField21.setText(data[9]);
           if(data.length>18) jLabel39.setText(data[18]);
           if(data.length>19 && wn.w.chkValue(data[19])) jCheckBox4.setSelected(true);
           else  jCheckBox4.setSelected(false);
           if(data.length>20) jTextField27.setText(data[20]);
           if(data.length>21) jTextField28.setText(data[21]);
           if(data.length>22) jTextField29.setText(data[22]);
           if(data.length>23 && wn.w.chkValue(data[23])) jCheckBox5.setSelected(true);
           else  jCheckBox5.setSelected(false);
           if(data.length>24) jTextField30.setText(data[24]);
           if(data.length>25 && wn.w.chkValue(data[25])) jCheckBox7.setSelected(true);
           else  jCheckBox7.setSelected(false);
           if(data.length>26 && wn.w.chkValue(data[26])) jCheckBox6.setSelected(true);
           else  jCheckBox6.setSelected(false);

           if(data.length>33) jTextField63.setText(data[33]);
           if(data.length>34 && wn.w.chkValue(data[34])) jCheckBox27.setSelected(true);
           else  jCheckBox27.setSelected(false);
           if(data.length>35) {
             if(data[35].length()>6 && data[35].indexOf(".class")==data[35].length()-6) data[35]=data[35].substring(0,data[35].indexOf(".class"));
             jTextField62.setText(data[35]);
           }
           if(data.length>36 && wn.w.chkValue(data[36])) jCheckBox29.setSelected(true);
           else  jCheckBox29.setSelected(false);
           if(data.length>37) jTextField64.setText(data[37]);
           if(data.length>38 && wn.w.chkValue(data[38])) jCheckBox28.setSelected(true);
           else  jCheckBox28.setSelected(false);
         }
         else jPanel79.setVisible(false);
         if(data[2].equalsIgnoreCase("Set device SN")) {
           jPanel78.setVisible(true);
           if(data.length>43) jComboBox32.setSelectedItem(data[43]);
           if(data.length>44) {
             jComboBox41.setSelectedItem(data[44]);
             if(data.length>45 && data[44].equalsIgnoreCase("Fixed column length")) {
               jTextField57.setText(data[45]);
               jLabel111.setVisible(true);
               jTextField57.setVisible(true);
             } else {
               jLabel111.setVisible(false);
               jTextField57.setVisible(false);
             } 
           }
           if(data.length>44 && !data[44].equalsIgnoreCase("Whole line")){
               jLabel112.setVisible(true);
               jTextField58.setVisible(true);
               if(data.length>47) jTextField58.setText(data[47]);
               jLabel166.setVisible(true);
               jTextField70.setVisible(true);
               jLabel164.setVisible(true);
               jTextField67.setVisible(true);
               if(data.length>46 && wn.w.chkValue(data[46])) jCheckBox33.setSelected(true);
               else  jCheckBox33.setSelected(false);
               if(data.length>55 && wn.w.chkValue(data[55])) jCheckBox41.setSelected(true);
               else  jCheckBox41.setSelected(false);
               if(data.length>65 && wn.w.chkValue(data[65])) jCheckBox39.setSelected(true);
               else  jCheckBox39.setSelected(false);
           } else {
               jLabel112.setVisible(false);
               jTextField58.setVisible(false);
               jLabel166.setVisible(false);
               jTextField70.setVisible(false);
               jLabel164.setVisible(false);
               jTextField67.setVisible(false);
           }
           if(data.length>48 && wn.w.chkValue(data[48])) jCheckBox36.setSelected(true);
           else  jCheckBox36.setSelected(false);
           if(data.length>49) jTextField59.setText(data[49]);
           if(data.length>50) jTextField60.setText(data[50]);
           if(data.length>57 && wn.w.chkValue(data[57])) jCheckBox42.setSelected(true);
           else  jCheckBox42.setSelected(false);
           if(data.length>58) jTextField71.setText(data[58]);
           if(data.length>59) jTextField72.setText(data[59]);
           if(data.length>67 && wn.w.chkValue(data[67])) jCheckBox40.setSelected(true);
           else  jCheckBox40.setSelected(false);
           if(data.length>68) jTextField68.setText(data[68]);
           if(data.length>69) jTextField69.setText(data[69]);

           if(data.length>27 && wn.w.chkValue(data[27])) jRadioButton10.setSelected(true);
           else  jRadioButton11.setSelected(true);
         }
         else jPanel78.setVisible(false);
         if(data[2].equalsIgnoreCase("Send command")) {
           jPanel85.setVisible(true);
           if(data.length>10) jComboBox30.setSelectedItem(data[10]);
           if(data.length>11) jTextField31.setText(data[11]);
           if(data.length>12 && wn.w.chkValue(data[12])) jCheckBox8.setSelected(true);
           else  jCheckBox8.setSelected(false);
           if(data.length>13) jComboBox31.setSelectedItem(data[13]);
           if(data.length>14 && wn.w.chkValue(data[14])) jCheckBox11.setSelected(true);
           else  jCheckBox11.setSelected(false);
           if(data.length>15) jTextField32.setText(data[15]);
         }
         else jPanel85.setVisible(false);
         if(data.length>70) jTextField54.setText(data[70]);
         if(data[2].equalsIgnoreCase("Open URL")) {
           jPanel26.setVisible(true);
          } else jPanel26.setVisible(true);
         showActionItem(data[2]);
       }
     }
  }
 }
void showChart(){
    if(chartList.getSelectedIndex()>-1){
     String sel=(String)chartList.getSelectedValue();
     if(chartTM.get(sel)!=null){
       String data[]=ylib.csvlinetoarray((String)chartTM.get(sel));
       if(data.length>1) jComboBox35.setSelectedItem(new Color(Integer.parseInt(data[1])));
       if(data.length>2) jTextField47.setText(data[2]);
       if(data.length>3) jTextField48.setText(data[3]);
       if(data.length>4) jTextField49.setText(data[4]);
       if(data.length>5) jTextField50.setText(data[5]);
       if(data.length>6) jTextField33.setText(data[6]);
       if(data.length>7) jTextField34.setText(data[7]);
       if(data[8].equalsIgnoreCase("Y")) jCheckBox12.setSelected(true); else jCheckBox12.setSelected(false);
       if(data[9].equalsIgnoreCase("Y")) jCheckBox13.setSelected(true); else jCheckBox13.setSelected(false);
       if(data[10].equals("1")) jRadioButton2.setSelected(true); else jRadioButton3.setSelected(true);
       if(data.length>11) jTextField35.setText(data[11]);
       if(data.length>12) jTextField36.setText(data[12]);
       if(data.length>13) jTextField37.setText(data[13]);
       if(data.length>14) jTextField38.setText(data[14]);
       if(data[15].equals("1")) jRadioButton4.setSelected(true); else jRadioButton5.setSelected(true);
       if(data[16].equalsIgnoreCase("Y")) jCheckBox12.setSelected(true); else jCheckBox14.setSelected(false);

       if(data[19].equalsIgnoreCase("Y")) jCheckBox15.setSelected(true); else jCheckBox15.setSelected(false);
       if(data[20].equalsIgnoreCase("Y")) jCheckBox16.setSelected(true); else jCheckBox16.setSelected(false);

       if(data[23].equalsIgnoreCase("Y")) jCheckBox19.setSelected(true); else jCheckBox19.setSelected(false);
       if(data[24].equalsIgnoreCase("Y")) jCheckBox20.setSelected(true); else jCheckBox20.setSelected(false);
       if(data[25].equalsIgnoreCase("Y")) jCheckBox21.setSelected(true); else jCheckBox21.setSelected(false);
       if(data[26].equalsIgnoreCase("Y")) jCheckBox22.setSelected(true); else jCheckBox22.setSelected(false);
       if(data[27].equalsIgnoreCase("Y")) jCheckBox23.setSelected(true); else jCheckBox23.setSelected(false);
       if(data.length>28) jTextField43.setText(data[28]);
       if(data.length>29) jTextField44.setText(data[29]);
       if(data[30].equalsIgnoreCase("Y")) jCheckBox24.setSelected(true); else jCheckBox24.setSelected(false);
       if(data.length>31) jComboBox42.setSelectedItem(data[31]);
       if(data[32].equalsIgnoreCase("Y")) jCheckBox26.setSelected(true); else jCheckBox26.setSelected(false);
       if(data.length>33) jTextField52.setText(data[33]);
       if(data.length>34) jTextField51.setText(data[34]);
       }
     }
}
void showCurve(){
    if(curveList.getSelectedIndex()>-1){
     String sel=(String)curveList.getSelectedValue();
     if(curveTM.get(sel)!=null){
       String data[]=ylib.csvlinetoarray((String)curveTM.get(sel));

       if(data.length>3) jComboBox34.setSelectedItem(data[3]);
       if(data.length>4) jComboBox40.setSelectedItem(data[4]);
       if(data.length>5) jComboBox51.setSelectedItem(data[5]);
       if(data.length>6) jComboBox52.setSelectedItem(data[6]);
       if(data.length>7) jLabel91.setText(data[7]);
       if(data.length>8) jComboBox33.setSelectedItem(data[8]);
       if(data.length>9) jComboBox38.setSelectedItem(new Color(Integer.parseInt(data[9])));
       if(data.length>10) jComboBox50.setSelectedItem(data[10]);
       }
     }
}

  boolean confirmExit() {
    if (continueMonitor) {
    int an = JOptionPane.showConfirmDialog(this, bundle2.getString("CrInstrument.xy.msg95"), bundle2.getString("CrInstrument.xy.msg96"), JOptionPane.CANCEL_OPTION | JOptionPane.YES_OPTION);
    return (an == JOptionPane.YES_OPTION);
    }
    return true;
  }

  void updateHistoryFile(int from) {
    if (wn.w.getHVar("a_monitor") != null && wn.w.getHVar("a_monitor").equalsIgnoreCase("true")) {
      sysLog("updateHistoryFile(), from " + from);
    }
    if (getPropsString("record-directory").length() < 1) {
      props.setProperty("record-directory", System.getProperty("user.dir"));
    }
    File files[] = new File(getPropsString("record-directory")).listFiles();
    Iterator it = stations.keySet().iterator();
    for (; it.hasNext();) {
      String station = (String) it.next();
      String rawFile = getRawFileName(station);
      String calculatedFile = getCalculatedFileName(station);

      int rawMax = 0, calculatedMax = 0;
      for (int i = 0; i < files.length; i++) {
        if (files[i].getName().toLowerCase().indexOf(rawFile.toLowerCase()) == 0 && files[i].getName().length() > (rawFile.length() + 1)
                && files[i].getName().charAt(rawFile.length()) == '.') {
          String tmp = files[i].getName().substring(rawFile.length() + 1);
          if (wn.isNumeric(tmp) && Integer.parseInt(tmp) > rawMax) {
            rawMax = Integer.parseInt(tmp);
          }
        } else if (files[i].getName().toLowerCase().indexOf(calculatedFile.toLowerCase()) == 0 && files[i].getName().length() > (calculatedFile.length() + 1)
                && files[i].getName().charAt(calculatedFile.length()) == '.') {
          String tmp = files[i].getName().substring(calculatedFile.length() + 1);
          if (wn.isNumeric(tmp) && Integer.parseInt(tmp) > rawMax) {
            calculatedMax = Integer.parseInt(tmp);
          }
        }
      }
      rawMax++;
      calculatedMax++;
      if (new File(getPropsString("record-directory") + File.separator + rawFile).exists()) {
        new File(getPropsString("record-directory") + File.separator + rawFile).renameTo(new File(getPropsString("record-directory") + File.separator + rawFile + "." + rawMax));
      }
      if (new File(getPropsString("record-directory") + File.separator + calculatedFile).exists()) {
        new File(getPropsString("record-directory") + File.separator + calculatedFile).renameTo(new File(getPropsString("record-directory") + File.separator + calculatedFile + "." + calculatedMax));
      }
      currentCalculatedRN.put(station, "0");

    }
    updateHistoryRecord = false;
  }

  void updateCurrentSensorData() {
    Iterator it = sensors.keySet().iterator();
    for (; it.hasNext();) {
      String key = (String) it.next();

      String info2[] = ylib.csvlinetoarray((String) sensors.get(key));

      double vK = 1.0, vT0 = 0, vA0 = 0.0, vB0 = 0.0, offsetA = 0.0, offsetB = 0.0, aG = 1.0, bG = 1.0, dA = 0.0, dB = 0.0, dT = 0.0;
      if (wn.isNumeric(info2[2])) {
        vA0 = Double.parseDouble(info2[2]);
      }
      if (vA0 > 9999.0) {
        vA0 = 0.0;
      }
      if (wn.isNumeric(info2[26])) {
        vB0 = Double.parseDouble(info2[26]);
      }
      if (vB0 > 9999.0) {
        vB0 = 0.0;
      }
      if (wn.isNumeric(info2[3])) {
        offsetA = Double.parseDouble(info2[3]);
      }
      if (wn.isNumeric(info2[27])) {
        offsetB = Double.parseDouble(info2[27]);
      }
      if (wn.isNumeric(info2[4])) {
        aG = Double.parseDouble(info2[4]);
      }
      if (wn.isNumeric(info2[28])) {
        bG = Double.parseDouble(info2[28]);
      }
      if (wn.isNumeric(info2[17])) {
        dA = Double.parseDouble(info2[17]);
      }
      if (wn.isNumeric(info2[18])) {
        dB = Double.parseDouble(info2[18]);
      }
      if (wn.isNumeric(info2[9])) {
        vK = Double.parseDouble(info2[9]);
      }
      if (wn.isNumeric(info2[10])) {
        vT0 = Double.parseDouble(info2[10]);
      }
      if (wn.isNumeric(info2[11])) {
        dT = Double.parseDouble(info2[11]);
      }

           

      info2[20] = formatValue(dA, 7, 4).trim();
      info2[19] = formatValue(dB, 7, 4).trim();
      dT = Math.round(dT * 100.0) / 100.0;
      info2[13] = String.valueOf(dT);

      sensors.put(key, ylib.arrayToCsvLine(info2));

    }
    showSensorData(currentTable2Row);
    dataUpdated = true;
  }

  String spc(int n) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < n; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }

  String zero(int n) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < n; i++) sb.append("0");
    return sb.toString();
  }
void showStation(){
  if(stationList.getSelectedValue()==null){
    if(stationList.getModel().getSize()>0) {
      stationList.setSelectedIndex(0);
      showTableThread.setAction((String)stationList.getSelectedValue());

    }
  } else {
    showTableThread.setAction((String)stationList.getSelectedValue());

  }
}

  void showStation(String station) {
    int statRow = 0, statAll = 0;

    if (station != null) {
      boolean newStation = !station.equals(currentStation);
      if (newStation) {
        currentStation = station;
        int rcount = jTable2.getRowCount();
        for (int i = rcount - 1; i > -1; i--) {
          ((DefaultTableModel) jTable2.getModel()).removeRow(i);
        }
      }
      TreeMap sensorsC = (TreeMap) sensors.clone();

      Iterator it = sensorsC.keySet().iterator();
      int inx = 0;
      for (; it.hasNext();) {
        statRow = 0;
        String key = (String) it.next();
        String info[] = ylib.csvlinetoarray(key);
        String name = info[0];
        String device = info[1];
        String model = info[2];
        String sn = info[3];
        String dataName = info[4];
        if (station.equals(name)) {

          String info2[] = ylib.csvlinetoarray((String) sensors.get(key));

          if (info2[25].equals("1")) {

            String info17 = info2[17].trim(), info20 = info2[20].trim(),
                    info3 = info2[3].trim(), info27 = info2[27].trim(),
                    info2A = info2[2].trim(), info26 = info2[26].trim();
            double d5 = 1000000.0, d6 = -1000000.0, d7 = 1000000.0, d8 = -1000000.0, d17 = 0.0, d20 = 0.0;
            if (isNumeric(info2[5])) {
              d5 = Double.parseDouble(info2[5]);
            }
            if (isNumeric(info2[6])) {
              d6 = Double.parseDouble(info2[6]);
            }
            if (isNumeric(info2[7])) {
              d7 = Double.parseDouble(info2[7]);
            }
            if (isNumeric(info2[8])) {
              d8 = Double.parseDouble(info2[8]);
            }
            if (isNumeric(info17)) {
              d17 = Double.parseDouble(info17);
            }
            if (isNumeric(info20)) {
              d20 = Double.parseDouble(info20);
            }

            if (isNumeric(info2[20])) {
              if (d20 > d7 || d20 < d8) {
                statRow = 2;
              } else if (d20 > d5 || d20 < d6) {
                if (statRow == 0) {
                  statRow = 1;
                }
              }

            }
            if (jTable2.getModel().getRowCount() < inx + 1) {
              ((DefaultTableModel) jTable2.getModel()).addRow(new Object[jTable2.getModel().getColumnCount()]);
            }
            jTable2.getModel().setValueAt(info2[1], inx, 0);
            jTable2.getModel().setValueAt(info2[2], inx, 1);
            jTable2.getModel().setValueAt(info2[3], inx, 2);
            jTable2.getModel().setValueAt(info2[4], inx, 3);

            jTable2.getModel().setValueAt(info20+(statRow==2? "  ":(statRow==1? " ":"")), inx, 4);

            if (statRow == 2) {
              jTable2.getModel().setValueAt(bundle2.getString("CrInstrument.xy.msg97"), inx, 5);
            } else if (statRow == 1) {
              jTable2.getModel().setValueAt(bundle2.getString("CrInstrument.xy.msg98"), inx, 5);
            } else {
              jTable2.getModel().setValueAt(bundle2.getString("CrInstrument.xy.msg99"), inx, 5);
            }
            if (statRow > statAll) {
              statAll = statRow;
            }
            inx++;
          }
        }
      }

      changeStation = false;
      if (currentStat != statAll) {
        currentStat = statAll;
      }

      int rcount2 = jTable2.getRowCount();
      if (rcount2 > inx) {
        for (int i = rcount2 - 1; i >= inx; i--) {
          ((DefaultTableModel) jTable2.getModel()).removeRow(i);
        }
      }

      if (inx > 0) {
        String id = "";
        if (currentSensorID == null || currentSensorID.length() < 1 || newStation) {
          id = (String) jTable2.getModel().getValueAt(0, 0)+","+(String) jTable2.getModel().getValueAt(0, 1)+","+
                    (String) jTable2.getModel().getValueAt(0, 2)+","+(String) jTable2.getModel().getValueAt(0, 3);
          currentTable2Row = 0;
          currentSensorID = currentStation + "," + id;
        } else {
          boolean foundCurrentID = false;
          int rows = jTable2.getRowCount();
          for (int i = 0; i < rows; i++) {
            if ((station + "," + (String) jTable2.getModel().getValueAt(i, 0)+","+(String) jTable2.getModel().getValueAt(i, 1)+","+
                    (String) jTable2.getModel().getValueAt(i, 2)+","+(String) jTable2.getModel().getValueAt(i, 3)).equals(currentSensorID)) {
              foundCurrentID = true;
              currentTable2Row = i;
              break;
            }
          }
          if (!foundCurrentID) {
            id = (String) jTable2.getModel().getValueAt(0, 0)+","+(String) jTable2.getModel().getValueAt(0, 1)+","+
                    (String) jTable2.getModel().getValueAt(0, 2)+","+(String) jTable2.getModel().getValueAt(0, 3);
            currentTable2Row = 0;
            currentSensorID = station + "," + id;
          }
          id = currentSensorID;
        }
        int row = currentTable2Row;
        if (id != null && id.length() > 0) {
          showSensorData(row);
        }
      }

      showChartThread.setAction(currentSensorID);

    }
    else {
      int rcount = jTable2.getRowCount();
      for (int i = rcount - 1; i > -1; i--) {
        ((DefaultTableModel) jTable2.getModel()).removeRow(i);
      }
    }
  }
   String getRound2(double value,int rN){
       return getRound2(""+value,rN);
   }

   String getRound2(String value,int rN){
       String rtn=value;

       if(value!=null && value.length()>0 && isNumeric(value)){
           double v1=Double.parseDouble(value);
           if(rN==0) return ""+Math.round(v1);
           double v2=Math.pow(10.0, (double)rN);
           v1=v1*v2;
           rtn= String.valueOf(((double)Math.round(v1)) /v2);

             int inx=rtn.indexOf(".");
             if(inx+rN+1>rtn.length()){
               int n=inx+rN+1-rtn.length();
               rtn=rtn+"0000000000".substring(10-n);
             }

       }

       return rtn;
   }
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {

        

    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(CrInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(CrInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(CrInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(CrInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new CrInstrument().setVisible(true);
      }
    });
  }

  public class MyTableCellRenderer extends DefaultTableCellRenderer {

    Color lightlightgray = new Color(240, 240, 240);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      if (column == 5 && value != null) {
        String v = (String) value;
        if (v.equalsIgnoreCase(bundle2.getString("CrInstrument.xy.msg97"))) {
          setBackground(Color.red);
        } else if (v.equalsIgnoreCase(bundle2.getString("CrInstrument.xy.msg99"))) {
          setBackground(Color.green);
        } else if (v.equalsIgnoreCase(bundle2.getString("CrInstrument.xy.msg98"))) {
          setBackground(Color.yellow);
        } else if (row % 2 == 0) {
          comp.setBackground(Color.white);
        } else {
          comp.setBackground(lightlightgray);
        }
      } else if ((column == 4) && value != null) {
        String v = (String) value;
        if (v.indexOf("  ") > -1) {
          setBackground(Color.red);
        } else if (v.indexOf(" ") > -1) {
          setBackground(Color.yellow);
        } else if (row == currentTable2Row) {
          comp.setBackground(Color.CYAN);
        } else if (row % 2 == 0) {
          comp.setBackground(Color.white);
        } else {
          comp.setBackground(lightlightgray);
        }
      } else {
        if (row == currentTable2Row) {
          comp.setBackground(Color.CYAN);
        } else if (row % 2 == 0) {
          comp.setBackground(Color.white);
        } else {
          comp.setBackground(lightlightgray);
        }

      }
      if (table.isCellSelected(row, column)) {
        setForeground(Color.black);
      } else if (table.isRowSelected(row)) {
        setForeground(Color.black);
      } else if (table.isColumnSelected(column)) {
        setForeground(Color.black);
      } else {
        setForeground(Color.black);
      }
      return comp;
    }
  }
  class MyCellRenderer extends JButton implements ListCellRenderer {  
     public MyCellRenderer() {  
         setOpaque(true); 

     }
     boolean b=false;
    @Override
    public void setBackground(Color bg) {

         if(!b)
         {
             return;
         }

        super.setBackground(bg);
    }
     public Component getListCellRendererComponent(  
         JList list,  
         Object value,  
         int index,  

         boolean isSelected,  
         boolean cellHasFocus)  
     {  

         b=true;
         setText(" ");           
         setBackground((Color)value);        
         b=false;
         return this;  
     }  
}
class ShowStationTableThread extends Thread{
CrInstrument instrument;

  boolean isSleep=false;
  String key="";
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
  public void run(){
      while(true){
          try{
              isSleep=true;
              sleep(waitTime);
          }catch(InterruptedException e){
             isSleep=false;
          }
        showStation(key);
      }
  }
   public void setAction(String key){
    this.key=key;
    if(isSleep) this.interrupt();
}
}
class ShowStationChartThread extends Thread{
CrInstrument instrument;

  boolean isSleep=false;
  String key="";
  long waitTime=1000L*60L*60L*24L,waitTime2=1L;
  public void run(){
      while(true){
          try{
              isSleep=true;
              sleep(waitTime);
          }catch(InterruptedException e){
             isSleep=false;
          }
        showCurve(key);
      }
  }
   public void setAction(String key){
    this.key=key;
    if(isSleep) this.interrupt();
}
}

    private javax.swing.JCheckBox CBUseEngineerUnit;
    private javax.swing.JPanel FTPPanel;
    private javax.swing.JPanel NodeMgntPanel;
    private javax.swing.JPanel UIPanel;
    private javax.swing.JList actionList;
    private javax.swing.JList actionList2;
    public javax.swing.JButton btnConnect;
    private javax.swing.JButton btnEditSrc;
    private javax.swing.JButton btnLogoutAdmin;
    public javax.swing.JButton btnStart;
    private javax.swing.JButton btnTestEMail;
    private javax.swing.JButton btnTestSMS;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    public javax.swing.JButton button02;
    private javax.swing.JButton button03;
    private javax.swing.JButton button04;
    private javax.swing.JButton button05;
    private javax.swing.JButton button06;
    private javax.swing.JButton button07;
    private javax.swing.JButton button08;
    private javax.swing.JButton button09;
    private javax.swing.JButton button10;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JRadioButton byChartGroup;
    private javax.swing.JRadioButton byDataName;
    private javax.swing.JRadioButton byDevice;
    private javax.swing.JRadioButton byModel;
    private javax.swing.JRadioButton byStation;
    private javax.swing.JCheckBox cbAutoAdjustY;
    private javax.swing.JCheckBox cbRemark;
    private javax.swing.JCheckBox cbZero;
    private javax.swing.JComboBox chartGroupCB;
    private javax.swing.JList chartList;
    private javax.swing.JPanel chartOptionPanel;
    public javax.swing.JCheckBox chkSumCB;
    public javax.swing.JComboBox chkSumCBB;
    private javax.swing.JButton clearSendBtn;
    private javax.swing.JButton clearShowBtn;
    private javax.swing.JList conditionList;
    private javax.swing.JList conditionList2;
    public javax.swing.JCheckBox continueSendCB;
    public javax.swing.JCheckBox crnlCB;
    private javax.swing.JList curveList;
    private javax.swing.JList eventList;
    private javax.swing.JMenuItem fileMenuItem01;
    private javax.swing.JMenuItem fileMenuItem02;
    private javax.swing.JMenuItem fileMenuItem03;
    private javax.swing.JMenuItem fileMenuItem04;
    private javax.swing.JMenuItem fileMenuItem05;
    private javax.swing.JMenuItem fileUpLoadMenuItem;
    private javax.swing.JMenuItem helpMenuItem01;
    private javax.swing.JMenuItem helpMenuItem02;
    private javax.swing.JMenuItem helpMenuItem03;
    private javax.swing.JMenuItem helpMenuItem04;
    private javax.swing.JMenuItem helpMenuItem05;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox43;
    private javax.swing.JCheckBox jCheckBox44;
    private javax.swing.JCheckBox jCheckBox45;
    private javax.swing.JCheckBox jCheckBox46;
    private javax.swing.JCheckBox jCheckBox48;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox17;
    private javax.swing.JComboBox jComboBox18;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox20;
    private javax.swing.JComboBox jComboBox21;
    private javax.swing.JComboBox jComboBox22;
    private javax.swing.JComboBox jComboBox23;
    private javax.swing.JComboBox jComboBox24;
    private javax.swing.JComboBox jComboBox25;
    private javax.swing.JComboBox jComboBox26;
    private javax.swing.JComboBox jComboBox27;
    private javax.swing.JComboBox jComboBox28;
    private javax.swing.JComboBox jComboBox29;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox30;
    private javax.swing.JComboBox jComboBox31;
    private javax.swing.JComboBox jComboBox32;
    private javax.swing.JComboBox jComboBox33;
    private javax.swing.JComboBox jComboBox34;
    private javax.swing.JComboBox jComboBox35;
    private javax.swing.JComboBox jComboBox36;
    private javax.swing.JComboBox jComboBox37;
    private javax.swing.JComboBox jComboBox38;
    private javax.swing.JComboBox jComboBox39;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox40;
    private javax.swing.JComboBox jComboBox41;
    private javax.swing.JComboBox jComboBox42;
    private javax.swing.JComboBox jComboBox43;
    private javax.swing.JComboBox jComboBox44;
    private javax.swing.JComboBox jComboBox45;
    private javax.swing.JComboBox jComboBox46;
    private javax.swing.JComboBox jComboBox47;
    private javax.swing.JComboBox jComboBox48;
    private javax.swing.JComboBox jComboBox49;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox50;
    private javax.swing.JComboBox jComboBox51;
    private javax.swing.JComboBox jComboBox52;
    private javax.swing.JComboBox jComboBox53;
    private javax.swing.JComboBox jComboBox54;
    private javax.swing.JComboBox jComboBox55;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel247;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel273;
    private javax.swing.JLabel jLabel274;
    private javax.swing.JLabel jLabel275;
    private javax.swing.JLabel jLabel276;
    private javax.swing.JLabel jLabel277;
    private javax.swing.JLabel jLabel278;
    private javax.swing.JLabel jLabel279;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel280;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel283;
    private javax.swing.JLabel jLabel284;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel296;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel302;
    private javax.swing.JLabel jLabel303;
    private javax.swing.JLabel jLabel304;
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JList jList10;
    private javax.swing.JList jList9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel131;
    private javax.swing.JPanel jPanel132;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel49;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JPasswordField jPasswordField6;
    private javax.swing.JPasswordField jPasswordField7;
    private javax.swing.JPasswordField jPasswordField8;
    private javax.swing.JPasswordField jPasswordField9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private ci.LightPanel lightPanel2;
    private javax.swing.JCheckBox onlyReceiveCB;
    public javax.swing.JList receiveList;
    private javax.swing.JTextPane receiveTP;
    public javax.swing.JCheckBox saveFileCB;
    public javax.swing.JRadioButton send16RB;
    private javax.swing.JButton sendBtn;
    public javax.swing.JTextField sendIntervalTF;
    public javax.swing.JList sendList;
    public javax.swing.JRadioButton sendStrRB;
    private javax.swing.JTextArea sendTA;
    public javax.swing.JRadioButton show16RB;
    private javax.swing.JCheckBox showAlarmRB;
    public javax.swing.JCheckBox showCB;
    public javax.swing.JCheckBox showSrcCB;
    public javax.swing.JRadioButton showStrRB;
    public javax.swing.JCheckBox showSysMsgCB;
    public javax.swing.JCheckBox showTimeCB;
    public javax.swing.JList stationList;
    private javax.swing.JButton stopContinueSendBtn;
    private javax.swing.JMenuItem toolMenuItem01;
    private javax.swing.JMenuItem toolMenuItem02;
    private javax.swing.JMenuItem toolMenuItem03;
    private javax.swing.JMenuItem toolMenuItem04;
    private javax.swing.JMenuItem toolMenuItem05;
    private javax.swing.JButton updateActionBtn;
    private javax.swing.JButton updateConditionBtn;

}