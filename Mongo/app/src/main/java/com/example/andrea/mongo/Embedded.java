package com.example.andrea.mongo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 30/04/2018.
 */


public class Embedded {

    @SerializedName("_embedded")
    @Expose
    private ArrayList<VideoGames> embedded = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("_returned")
    @Expose
    private Integer returned;

    public ArrayList<VideoGames> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(ArrayList<VideoGames> embedded) {
        this.embedded = embedded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

}

class VideoGames {
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("_etag")
    @Expose
    private Etag etag;
    @SerializedName("creatori")
    @Expose
    private String creatori;
    @SerializedName("descrizione")
    @Expose
    private String descrizione;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("name")
    @Expose
    private String name;

    public VideoGames(String nome, String creatori, String descrizione) {
        this.nome = nome;
        this.creatori = creatori;
        this.descrizione = descrizione;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Etag getEtag() {
        return etag;
    }

    public void setEtag(Etag etag) {
        this.etag = etag;
    }

    public String getCreatori() {
        return creatori;
    }

    public void setCreatori(String creatori) {
        this.creatori = creatori;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


class Id {

    @SerializedName("$oid")
    @Expose
    private String $oid;

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

}

class Etag {

    @SerializedName("$oid")
    @Expose
    private String $oid;

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

}
