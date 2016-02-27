package com.osahub.rachit.navdrawertemplate.Classes;

/**
 * Created by sushil on 2/11/16.
 */
public class PhotoAlbum {
    private String mTitle;
    private String mThumbUrl;
//    private String mCreate;
    private String mUpdate;
    private String mDescription;
    private int mId;
    private String[] mImagesUrl;

    public PhotoAlbum(String ThumbUrl, String Title, int id, String created, String updated, String description, String[] stringArrayUrl){
        mTitle = Title;
        mThumbUrl = ThumbUrl;
        mId = id;
//        mCreate = created;
        mUpdate = updated;
        mDescription = description;
        mImagesUrl = stringArrayUrl;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getUrl(){
        return mThumbUrl;
    }

    public int getId(){
        return mId;
    }

//    public String getCreate(){
//        return mCreate;
//    }

    public String getUpdate(){
        return mUpdate;
    }

    public String getDescription(){
        return mDescription;
    }

    public String[] getImagesUrl(){
        return mImagesUrl;
    }

    public void setImagesUrl(String[] imagesUrl){
        mImagesUrl = imagesUrl;
    }

    public void setDescription(String description){
        mDescription = description;
    }

//    public void setCreate(String created){
//        mCreate = created;
//    }

    public void setUpdate(String updated){
        mUpdate = updated;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public void setUrl(String url){
        mThumbUrl = url;
    }

    public void setId(int id){
        mId = id;
    }

}
