package com.JEJU.yeonjigonji_backend.entity;


import com.JEJU.yeonjigonji_backend.constant.Gender;
import com.JEJU.yeonjigonji_backend.constant.Role;
import com.JEJU.yeonjigonji_backend.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setEmail(memberFormDto.getEmail());
        member.setAge(memberFormDto.getAge());
        member.setGender(memberFormDto.getGender());
        member.setRole(Role.USER);
        return member;
    }
}
