package info.dt.qlcv.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TypeTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeTopic")
    private int idTypeTopic;

    @Column(name = "nameTypeTopic")
    private String nameTypeTopic;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "typeTopic", cascade = CascadeType.ALL)
    private Set<Topic> topicSet;

    public TypeTopic() {
    }

    public TypeTopic(String nameTypeTopic, int status) {
        this.nameTypeTopic = nameTypeTopic;
        this.status = status;
    }

    public int getIdTypeTopic() {
        return idTypeTopic;
    }

    public void setIdTypeTopic(int idTypeTopic) {
        this.idTypeTopic = idTypeTopic;
    }

    public String getNameTypeTopic() {
        return nameTypeTopic;
    }

    public String getNameTypeTopicUI() {
        return "\""+nameTypeTopic + "\"";
    }

    public void setNameTypeTopic(String nameTypeTopic) {
        this.nameTypeTopic = nameTypeTopic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
