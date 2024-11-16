package com.ahmet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable=false)
    @JsonIgnore
    Post post;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy: When we retrieve a post, its user will not be retrieved immediately (until it is explicitly accessed for the first time -> post.getUser() . ||| [ilişkileri anlamak için örnek durum: Bir okulun bir adresi olabilir (one-to-one). Bir okulun birçok öğretmeni olabilir (one-to-many). Birçok öğretmenin bir okulu vardır (many-to-one). Birçok öğretmenin birçok öğrencisi ve aynı zamanda birçok öğrencinin birçok öğretmeni olabilir (many-to-many)]
    @JoinColumn(name="user_id", nullable=false) // The foreign key column in the 'post' table shall be "user_id". This 'user_id' will reference the 'id' column in the 'user' table. So, 'post' table will have these columns: id, text, title, user_id (foreign-key). 'nullable=false' means the 'user_id' column can't be null; which means that every post must be associated with a user.
    @JsonIgnore // Ignore the 'user' field when converting an object to JSON and a JSON to an object (serialization & deserialization). (we've already set it 'lazy', so we don't need to see it).
//    @OnDelete(action=OnDeleteAction.CASCADE) // When a post is deleted, delete the user of it as well (this is sth we don't want???).
    User user; // (önceden burda 'Long userId' yazıyordu, değiştirdik)

    @Lob
    @Column(columnDefinition="text") // (veritabanında String'i text olarak algılaması için yazdık; yoksa 'varchar255' olarak algılıyo)
    String text;

}
