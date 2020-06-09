package com.marketplace.produto.util;

import com.marketplace.produto.enums.TipoExportacao;
import com.marketplace.produto.model.Produto;
import com.marketplace.produto.repository.ProdutoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportUtils {
    @Autowired
    private ProdutoRepository produtoRepository;

    public String exportReportProdutos(TipoExportacao tipoExportacao) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\basan\\Desktop\\Report";
        List<Produto> produtos = produtoRepository.findAll();

        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(produtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        exportar(tipoExportacao,path,jasperPrint);

        return "report generated in path : " + path;
    }

    private void exportar(TipoExportacao tipoExportacao, String path, JasperPrint jasperPrint) throws JRException {

        switch (tipoExportacao) {
            case PDF:
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees." + tipoExportacao.getDescricao());
            case HTML:
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees." + tipoExportacao.getDescricao());
        }
    }
}
