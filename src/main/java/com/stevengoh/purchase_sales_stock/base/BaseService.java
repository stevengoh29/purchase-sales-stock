package com.stevengoh.purchase_sales_stock.base;

import com.stevengoh.purchase_sales_stock.exceptions.common.NotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public abstract class BaseService<R extends BaseRepository<E>, E extends BaseModel, D extends BaseDto> {
    private final R repository;
    private final ModelMapper modelMapper;

    public List<D> getList () {
        List<E> entities = repository.findAll();
        return entities.stream().map(this::convertEntityToDto).toList();
    }

    public Page<D> getList(Pageable pageable) {
        Page<E> entityPage = repository.findAll(pageable);
        List<D> list = entityPage.getContent().stream().map(this::convertEntityToDto).toList();
        return new PageImpl<>(list, pageable, entityPage.getTotalElements());
    }

    public D getById(Long id) {
        E entity = repository.findById(id).orElse(null);
        return convertEntityToDto(entity);
    }

    public D getById(UUID uuid) {
        E entity = repository.findByUuid(uuid).orElse(null);
        return convertEntityToDto(entity);
    }

    public D create(E entity) {
        E entitySaved = repository.save(entity);
        return convertEntityToDto(entitySaved);
    }

    public D update(Long id, E entity) {
        E existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null)
            throw new NotFoundException(String.format("Entity %s with id %s not found.", entity.getClass().getSimpleName(), id));

        E entityToSave = modelMapper.map(entity, getEntityClass());
        entityToSave.setId(id);
        entityToSave.setUuid(existingEntity.getUuid());

        E saved = repository.save(entityToSave);
        return convertEntityToDto(saved);
    }

    public void delete(Long id) {
        E entity = repository.findById(id).orElse(null);
        if(entity == null) throw new NotFoundException("Entity not found.");

        repository.delete(entity);
    }

    public void delete(UUID uuid) {
        E entity = repository.findByUuid(uuid).orElse(null);
        if(entity == null) throw new NotFoundException("Entity not found.");

        repository.delete(entity);
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    @SuppressWarnings("unchecked")
    private Class<D> getDtoClass() {
        return (Class<D>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2]);
    }

    private D convertEntityToDto(E e) {
        return modelMapper.map(e, getDtoClass());
    }

    private E convertEntityToNewEntity(E entity) {
        return modelMapper.map(entity, getEntityClass());
    }

    private E convertDtoToEntity(D d) {
        return modelMapper.map(d, getEntityClass());
    }
}
