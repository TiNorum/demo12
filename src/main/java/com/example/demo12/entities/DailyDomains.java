package com.example.demo12.entities;



import javax.persistence.*;

@Entity
@Table(name = "daily_domains ")
public class DailyDomains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domainname;
    private int hotness;
    private int price;
    private int x_value;
    private int yandex_tic;
    private int links;
    private int visitors;
    private String registrar;
    private int old;
    private String delete_date;
    private Boolean rkn;
    private Boolean judicial;
    private Boolean block;

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public int getHotness() {
        return hotness;
    }

    public void setHotness(int hotness) {
        this.hotness = hotness;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getX_value() {
        return x_value;
    }

    public void setX_value(int x_value) {
        this.x_value = x_value;
    }

    public int getYandex_tic() {
        return yandex_tic;
    }

    public void setYandex_tic(int yandex_tic) {
        this.yandex_tic = yandex_tic;
    }

    public int getLinks() {
        return links;
    }

    public void setLinks(int links) {
        this.links = links;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getDelete_date() {
        return delete_date;
    }

    public void setDelete_date(String delete_date) {
        this.delete_date = delete_date;
    }

    public boolean isRkn() {
        return rkn;
    }

    public void setRkn(boolean rkn) {
        this.rkn = rkn;
    }

    public boolean isJudicial() {
        return judicial;
    }

    public void setJudicial(boolean judicial) {
        this.judicial = judicial;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
