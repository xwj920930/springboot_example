package com.xwj.mockmvc.model;

public class B{
      private String name;
      public String getName(){
          return name;
      }
      public void setName(String name){
          this.name = name;
      }
      public String getSex(Integer sex){
          if(sex==1){
              return "man";
          }else{
              return "woman";
          }
      }
  }