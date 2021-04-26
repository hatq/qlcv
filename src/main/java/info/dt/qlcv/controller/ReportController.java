package info.dt.qlcv.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.dt.qlcv.dao.WorkDAO;
import info.dt.qlcv.entity.Work;
import info.dt.qlcv.model.WorkReport;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("api/document")
public class ReportController {
	
	@Autowired
	private WorkDAO workDao;

	@GetMapping()
    public void getDocument(HttpServletResponse response) throws IOException, JRException {
		String outFile = "D:\\HATQ\\Work\\qlcv\\template_doc.docx";
		
		List<Work> listWork = this.workDao.findAll();
		List<WorkReport> lstParam = listWork.stream().map(i -> {
			return toDomain(i, listWork.indexOf(i));
		}).collect(Collectors.toList());
		
		List<WorkReport> lstParam1 = listWork.stream().map(i -> {
			return toDomain(i, listWork.indexOf(i));
		}).collect(Collectors.toList());
		
		List<List<WorkReport>> lstItem = new ArrayList<List<WorkReport>>();
		lstItem.add(lstParam);
		lstItem.add(lstParam1);
		
		JRBeanCollectionDataSource itemsBean = new JRBeanCollectionDataSource(lstItem);
		
		Map<String, Object> params = new HashMap<>();
		params.put("CollectionBeanParam", itemsBean);
		
		InputStream input = new FileInputStream(new File("D:\\HATQ\\Work\\qlcv\\template_doc.jrxml"));
		
		JasperDesign jasperDesign = JRXmlLoader.load(input);
		
		/*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File exportReportFile = new File(outFile);    
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile)); 
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//        response.setHeader("Content-Disposition", "attachment;filename=jasperfile.docx");
//        response.setContentType("application/octetstream");
        exporter.exportReport();

        System.out.println("File Generated");
    }
	
	public WorkReport toDomain(Work work, int stt) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		WorkReport param = new WorkReport();
		param.setStt(stt + 1);
		param.setDonVi(work.getUnit().getTenDonVi());
		param.setNoiDungCv(work.getNoiDungCv());
		param.setTimeThucHien(formatter.format(work.getTimeThucHien()));
		param.setTimeHoanThanh(formatter.format(work.getTimeHoanThanh()));
		param.setBgd(work.getBgd());
		param.setNsth(work.getNsth() == null ? "" : work.getNsth());
		param.setKtkh(work.getKtkh() == null ? "" : work.getKtkh());
		param.setKtdt(work.getKtdt() == null ? "" : work.getKtdt());
		param.setDhtt(work.getDhtt() == null ? "" : work.getDhtt());
		param.setCntt(work.getCntt() == null ? "" : work.getCntt());
		param.setTtvt(work.getTtvt() == null ? "" : work.getTtvt());
		param.setKetQua(work.getKetQua());
		return param;
	}
}
