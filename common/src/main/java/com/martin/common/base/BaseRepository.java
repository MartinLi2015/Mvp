package com.martin.common.base;

import com.martin.common.data.Repository;

/**
 * Created by martin on 2017/5/6.
 */

public class BaseRepository {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Repository stu = null;
        try {
            stu = (Repository) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;

    }
}
