package com.github.skomaromi.bugsy.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class RssResponse {
    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<PostSearchResult> posts;

    public List<PostSearchResult> getPosts() {
        return posts;
    }
}