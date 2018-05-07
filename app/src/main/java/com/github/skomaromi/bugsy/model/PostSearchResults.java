package com.github.skomaromi.bugsy.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class PostSearchResults {
    // TODO: should this be inline?
    @ElementList//(inline = true)
    List<PostSearchResult> postList;

    public List<PostSearchResult> getPosts() {
        return postList;
    }
}
