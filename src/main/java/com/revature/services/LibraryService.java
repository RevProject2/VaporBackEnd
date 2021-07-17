package com.revature.services;

import java.util.List;

import com.revature.beans.Libraries;

public interface LibraryService extends GenericService<Libraries>{

	public List<Libraries> get(Integer id);
}
