package com.example.fitnesproject.domain;

import lombok.Data;

@Data
public class FitnessMembershipUserInfo {
    private  String name;
    private  String phone;
    private  String mail;
    private  MembershipOption option;
    private  double payedSum;
}
