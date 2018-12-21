package com.loushy.mall.action;

import java.util.ArrayList;
import java.util.List;

public class MyThread implements Runnable {
    private List<String> list = new ArrayList<String>();
    private int i;

    public  MyThread(List<String> list,int index){
        this.list = list;
        this.i = index;
    }

    @Override
    public void run() {
        for (int j=i;j<list.size()-1;j=j+4){
            System.out.println(Thread.currentThread().getName()+":"+list.get(j));
            try{
                Thread.sleep(3000);
            }catch (Exception e){

            }
        }
    }
}
