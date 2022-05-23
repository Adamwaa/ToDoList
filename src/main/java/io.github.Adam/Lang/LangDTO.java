package io.github.Adam.Lang;

public class LangDTO {

    private int id;
    private String code;

    public LangDTO(Lang lang) {
        this.id = lang.getId();
        this.code = lang.getCode();
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
