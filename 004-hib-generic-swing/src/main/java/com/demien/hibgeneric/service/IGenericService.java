package com.demien.hibgeneric.service;

import java.util.List;

import com.demien.hibgeneric.dao.DAOException;
import com.demien.hibgeneric.dao.IGenericDAO;

public interface IGenericService<T> extends IGenericDAO<T> {
  List<T> getAll() throws DAOException;
  void deleteAll() throws DAOException;
  Class<T> getElementClass();
}
