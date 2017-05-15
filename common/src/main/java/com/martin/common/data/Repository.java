package com.martin.common.data;

import com.martin.common.base.BaseRepository;

import java.util.Map;

import rx.Observable;

/**
 *
 */

public abstract class Repository<T> extends BaseRepository {
    public T data;
    public Map<String,String> param;
    public abstract Observable<T> getPageAt(int page);
}
