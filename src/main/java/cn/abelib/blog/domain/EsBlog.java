package cn.abelib.blog.domain;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by abel on 2018/1/17.
 * es的文档类
 */
@Document(indexName = "blog", type = "blog")
@XmlRootElement
public class EsBlog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field(index = FieldIndex.not_analyzed)
    private Long blogId;


    private String title;

    private String summary;

    private String content;

    @Field(index = FieldIndex.not_analyzed)
    private String username;

    @Field(index = FieldIndex.not_analyzed)
    private String avatar;

    @Field(index = FieldIndex.not_analyzed)
    private Timestamp createTime;

    @Field(index = FieldIndex.not_analyzed)
    private Integer readSize = 0;

    @Field(index = FieldIndex.not_analyzed)
    private Integer commentSize = 0;

    @Field(index = FieldIndex.not_analyzed)
    private Integer voteSize = 0;

    private String tags;

    protected EsBlog(){ // JPA要求

    }

    public EsBlog(String title, String content){
        this.title = title;
        this.content = content;
    }

    public EsBlog(String title, String summary, String content){
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public EsBlog(Long blogId, String title, String summary, String content, String username, String avatar, Timestamp createTime, Integer readSize, Integer commentSize, Integer voteSize, String tags) {
        this.blogId = blogId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.username = username;
        this.avatar = avatar;
        this.createTime = createTime;
        this.readSize = readSize;
        this.commentSize = commentSize;
        this.voteSize = voteSize;
        this.tags = tags;
    }

    public EsBlog(Blog blog){
        this.blogId = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.username = blog.getSummary();
        this.avatar = blog.getUser().getAvatar();
        this.createTime = blog.getCreateTime();
        this.readSize = blog.getReadSize();
        this.commentSize = blog.getCommentSize();
        this.voteSize = blog.getVoteSize();
        this.tags = blog.getTags();
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return String.format(
                "Blog[id='%s', title='%s', summary='%s', content='%s']",
                id, title, summary, content);
    }
}