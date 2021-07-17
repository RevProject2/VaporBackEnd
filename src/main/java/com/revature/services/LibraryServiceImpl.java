package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Libraries;
import com.revature.repos.LibraryDAO;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	private LibraryDAO ldao;
	
	@Autowired
	public LibraryServiceImpl(LibraryDAO ldao) {
		this.ldao = ldao;
	}

	@Override
	public Libraries getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libraries add(Libraries t) {
		return ldao.save(t);
	}

	@Override
	public List<Libraries> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libraries update(Libraries t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Libraries> get(Integer id) {
		return ldao.get(id);
	}

}
