package br.com.eduardofbom.ScreenMatch.infrastructure.model;

public enum GenreCategory {

    ACAO("Action"),
    COMEDIA("Comedy"),
    ROMANCE("Romance"),
    DRAMA("Drama"),
    CRIME("Crime"),
    FANTASIA("Fantasy"),
    TERROR("Horror"),
    MISTERIO("Mystery"),
    ANIMACAO("Animation"),
    AVENTURA("Adventure");

    private String categoryOmdb;

    GenreCategory(String categoryOmdb) {
        this.categoryOmdb = categoryOmdb;
    }

    public static GenreCategory fromString(String text) {
        for (GenreCategory genreCategory : GenreCategory.values()) {
            if (genreCategory.categoryOmdb.equalsIgnoreCase(text)) {
                return genreCategory;
            }
        }
        throw new IllegalArgumentException("No category found for the string '" + text + "'");
    }

}
