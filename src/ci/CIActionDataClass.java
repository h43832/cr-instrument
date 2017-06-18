
package ci;

import java.util.TreeMap;
public class CIActionDataClass {
   TreeMap actionCodeTM;
   CIDataClass dataClass;
   public CIActionDataClass(TreeMap actionTM, CIDataClass dataClass){
     this.actionCodeTM=actionTM; this.dataClass=dataClass;
   }
}