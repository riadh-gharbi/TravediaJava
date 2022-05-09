/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author snoussi amine
 */
public enum PostAudience {
    PUBLIC(0,"Public","/images/ic_public.png"),
    FRIENDS(1,"Friends","/images/ic_friend.png");

    private int id;
    private String name;
    private String imgSrc;

    PostAudience(int id, String name, String imgSrc) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
