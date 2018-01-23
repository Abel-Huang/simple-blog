package cn.abelib.blog.bean;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by abel on 2017/10/28.
 * Blog的实体类  博客
 */
@Entity
@Document(indexName = "blog", type = "blog")
public class Blog implements Serializable{
    private static final long serialVersionUID = -859673131481817067L;

    // 主键, 作为文档的id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 300)
    @Column(nullable = false)
    private String summary;

    @Basic(fetch = FetchType.LAZY)
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "use_id")
    private User user;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createTime;

    // 阅读量
    @Column(name = "readSize")
    private Integer readSize = 0;

    // 评论数
    @Column(name = "commentSize")
    private Integer commentSize = 0;

    // 点赞数
    @Column(name = "voteSize")
    private Integer voteSize = 0;

    // 标签
    @Column(name = "tags", length = 100)
    private String tags;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "comment", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "vote", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private List<Vote> votes;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Blog(){ // JPA要求

    }

    public Blog(String title, String summary, String content){
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Blog(String title, String summary){
        this.title = title;
        this.summary = summary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        this.commentSize = this.comments.size();
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *  添加评论
     * @param comment
     */
    public void addComment(Comment comment){
        this.comments.add(comment);
        this.commentSize = this.comments.size();
    }

    /**
     *  删除评论
     * @param commentId
     */
    public void removeComment(Long commentId){
        for (Comment comment : comments ){
            if (comment.getId() == commentId){
                comments.remove(comment);
                break;
            }
        }
        this.commentSize = this.comments.size();
    }

    /**
     *  添加点赞
     * @param vote
     */
    public boolean addVote(Vote vote){
        boolean isExist = false;
        for (Vote vote1 : this.getVotes()){
            if (vote1.getUser().getId() == vote.getUser().getId()){
                isExist = true;
                break;
            }
        }
        if (!isExist){
            this.votes.add(vote);
            this.voteSize = this.votes.size();
        }
        return isExist;
    }

    /**
     *  取消点赞
     * @param voteId
     */
    public void removeVote(Long voteId){
        for (Vote vote : votes ){
            if (vote.getId() == voteId){
                votes.remove(voteId);
                break;
            }
        }
        this.voteSize = this.votes.size();
    }

    @Override
    public String toString(){
        return String.format(
                "Blog[id='%s', title='%s', summary='%s', content='%s']",
                id, title, summary, content);
    }

    public Blog simpleBlog(){
        return new Blog(this.getTitle(), this.getSummary());
    }
}
