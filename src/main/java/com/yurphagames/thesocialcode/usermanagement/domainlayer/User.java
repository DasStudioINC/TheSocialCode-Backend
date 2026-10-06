package com.yurphagames.thesocialcode.usermanagement.domainlayer;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private UserHandle userHandle;

    @Embedded
    private ProfilePicture profilePicture;

    @Embedded
    private UserName userName;

    @Embedded
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

    @Column(name = "created_at", nullable = false)
    private LocalDate creationDate;

    @Valid
    @Embedded
    private EmailAddress email;

    @Embedded
    private AccountData accountData;

    public User(UserHandle handle, UserName name, UserInfo info, EmailAddress email, SubscriptionType type, AccountData data){
        this.userHandle = handle;
        this.userName = name;
        this.userInfo = info;
        this.email = email;
        this.subscriptionType = type;
        this.creationDate = LocalDate.now();
        this.accountData = data;
    }
}