package com.clinikktv.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Table(name = "audio_content")
@NamedQueries({
        @NamedQuery(name = "getAllAudioContents", query = "select q from AudioContentEntity q"),
        @NamedQuery(name = "getAllFreeAudioContents", query = "select q from AudioContentEntity q where q.isPaid = false"),
        @NamedQuery(name = "getAllPaidAudioContents", query = "select q from AudioContentEntity q where q.isPaid = true"),
        @NamedQuery(
                name = "audiobyId",
                query = "select q from AudioContentEntity q where q.uuid=:uuid"),
})
public class AudioContentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    @Size(max = 200)
    private String uuid;

    @Column(name = "audio_name")
    @Size(max = 255)
    @NotNull
    private String audioName;

    @Column(name = "description")
    @Size(max = 255)
    @NotNull
    private String description;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "is_paid")
    @NotNull
    private Boolean isPaid;


    public AudioContentEntity(Integer id, @Size(max = 200) @NotNull String uuid, @Size(max = 255) @NotNull String audioName, @Size(max = 255) @NotNull String description, @NotNull ZonedDateTime date, @NotNull Boolean isPaid) {
        this.id = id;
        this.uuid = uuid;
        this.audioName = audioName;
        this.description = description;
        this.date = date;
        this.isPaid = isPaid;
    }

    public AudioContentEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
