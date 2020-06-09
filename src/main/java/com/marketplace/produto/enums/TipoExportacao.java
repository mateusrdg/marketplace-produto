package com.marketplace.produto.enums;

public enum TipoExportacao {
    PDF("pdf"),
    HTML("html");

    private String descricao;

    TipoExportacao(String descricao) {
    }

    public String getDescricao() {
        return descricao;
    }
}
