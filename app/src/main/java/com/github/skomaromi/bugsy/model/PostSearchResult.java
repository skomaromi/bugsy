package com.github.skomaromi.bugsy.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "item", strict = false)
public class PostSearchResult {
    @Element(name = "title") private String mTitle;
    @Element(name = "description") private String mDescription;
    @Element(name = "link") private String mLink;
    @Element(name = "pubDate") private String mDate;
    @Element(name = "category") private String mCategory;
    @Element(name = "enclosure")
    private RssImage mImage;

    public PostSearchResult() {}

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    @Root()
    public static class RssImage{
        @Attribute(name = "url")
        private String url;

        RssImage() {}
    }

    public String getImageUrl() {
        return mImage.url;
    }
}