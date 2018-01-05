package com.demien.ddd.application.base;

public class BaseService<T extends BaseEntity> {

    protected BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public void update(T entity) {
        repository.update(entity);
    }

    public void delete(T entity) {
        repository.delete(entity.getId());
    }
}
