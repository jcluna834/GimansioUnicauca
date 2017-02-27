
package com.unicauca.gymadmdoc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ricardo
 */
@Named(value = "exportService")
@ApplicationScoped
public class ExportService {
   
   @PersistenceContext(unitName = "Gym_Adm_DocPU")
   private EntityManager em;
   
   private EntityManager getEm() {
      return em;
   }
   
   public String exportReporteRecaudo(int month, int year){
      String nameFile= "";
      
      //Obtener fecha actual
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      String fecha = dateFormat.format(new Date());
      
      try{
         FacesContext context = FacesContext.getCurrentInstance();
         ExternalContext externalContext = context.getExternalContext();
         ServletContext sc = (ServletContext)externalContext.getContext();
         String path = sc.getRealPath("/WEB-INF/control_mensual_recaudos.xlsx");
         File f = new File(path);
         FileInputStream file = new FileInputStream(f);
         XSSFWorkbook workbook = new XSSFWorkbook(file);
         XSSFSheet sheet = workbook.getSheetAt(0);
         
         int rownum = 0;
         Iterator<Row> rowIterator = sheet.iterator();
         int cellnum;
         
         while(rowIterator.hasNext()){
            XSSFRow row = (XSSFRow) rowIterator.next();
            rownum++;
            if(rownum == 7){
               break;
            }
            if(rownum == 4){
               Cell cell = row.getCell(6);
               cell.setCellValue("Fecha vigencia: "+fecha);
            }
            if(!rowIterator.hasNext()){
               row = sheet.createRow(rownum);
            }
         }
         
         Calendar c = Calendar.getInstance();
         c.set(year, month-1, 1);
         Date ini = c.getTime();
         c.set(year, month-1, lastDayOf(month));
         Date fin = c.getTime();
         Query query = getEm().createQuery("SELECT us.usuNombres, us.usuApellido1, us.usuIdentificacion, rp.rpagReferencia, oc.ocuDescripcion, rp.rpagFechaExpedicion, ir.irecFechaLimite, rp.rpagTotalRecibo FROM MrecInformacionRecaudo ir, MrecReciboPago rp, MuUsuario us, MuOcupacion oc WHERE rp.rpagReferencia = ir.rpagReferencia AND us.usuIdentificacion = ir.usuIdentificacion AND oc.ocuId = us.ocuId.ocuId AND rp.rpagFechaExpedicion BETWEEN :fini AND :ffin AND ir.irecEstadoRecaudo = :estado");
         query.setParameter("estado", "Pagado");
         query.setParameter("fini", ini);
         query.setParameter("ffin", fin);
         List<Object[]> lstRta = query.getResultList();
         
         Map<Integer, Object[]> data = new TreeMap<>();
         
         for(int i=0; i<lstRta.size(); i++){
            Object[] o = lstRta.get(i);
            data.put((i+1), new Object[]{(i+1), o[0]+" "+o[1], o[2], o[3], o[4], o[5], o[6], o[7]});
         }
         
         Set<Integer> keyset = data.keySet();
         
         for(Integer key : keyset){
            XSSFRow row = sheet.createRow(rownum++);
            Object[] objArray = data.get(key);
            
            for(cellnum =0; cellnum<=8; cellnum++){
               XSSFCellStyle style = workbook.createCellStyle();
               XSSFFont font = workbook.createFont();
               switch(cellnum){
                  case 1:
                     style.setBorderTop(XSSFCellStyle.BORDER_THIN);
                     style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
                     style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
                     break;
                  case 2:
                     style.setBorderTop(XSSFCellStyle.BORDER_THIN);
                     style.setBorderRight(XSSFCellStyle.BORDER_THIN);
                     style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
                     sheet.addMergedRegion(new CellRangeAddress(
                             rownum-1,
                             rownum-1,
                             1,
                             2
                     ));
                     break;
                  default:
                     style.setBorderTop(XSSFCellStyle.BORDER_THIN);
                     style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
                     style.setBorderRight(XSSFCellStyle.BORDER_THIN);
                     style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
                     break;
               }
               XSSFCell cell = row.createCell(cellnum);
               if(cellnum==0 || cellnum==1 || cellnum==3 || cellnum==4 || cellnum==6 || cellnum==7){
                  style.setAlignment(CellStyle.ALIGN_CENTER);
               }
               style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
               style.setWrapText(true);
               if(cellnum==0 || cellnum==1 || cellnum==5){
                  font.setBold(true);
               }else{
                  font.setBold(false);
               }
               style.setFont(font);
               cell.setCellStyle(style);
            }
            
            cellnum = 0;
            for(Object obj : objArray){
               if(cellnum == 2){
                  cellnum++;
               }
               Cell cell = row.getCell(cellnum);
               if(obj instanceof String){
                  cell.setCellValue((String)obj);
               }else if(obj instanceof Integer){
                  cell.setCellValue((Integer)obj);
               }else if(obj instanceof Long){
                  cell.setCellValue((Long)obj);
               }else if(obj instanceof Date){
                  cell.setCellValue(dateFormat.format((Date)obj));
               }
               cellnum++;
            }
         }
         file.close();
         
         try{
            nameFile = "Control mensual recaudos ("+c.get(Calendar.MONTH)+" - "+c.get(Calendar.YEAR)+").xlsx";
            File tempFile = new File("C:/Temp/"+nameFile);
            if(tempFile.exists()){
               tempFile.delete();
            }
            FileOutputStream out = new FileOutputStream(tempFile);
            workbook.write(out);
            out.close();
            
         }catch(Exception e){
            e.printStackTrace();
         }
        
      }catch(Exception e){
         e.printStackTrace();
      }
      
      return nameFile; 
   }
   
   private int lastDayOf(int month){
      int result;
      switch(month){
         case 1:
         case 3:
         case 5:
         case 7:
         case 8:
         case 10:
         case 12:
            result = 31;
            break;
            
         case 2:
            result = 28;
            break;
         
         default:
            result = 30;
            break;
      }
      return result;
   }
   
}
