package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;
import com.jsmall.mapper.AdProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdProductServiceImpl implements AdProductService {
	
	private final AdProductMapper adProductMapper;

	@Override
	public void pro_insert(ProductVO vo) {
		// TODO Auto-generated method stub
		adProductMapper.pro_insert(vo);
	}

	@Override
	public List<ProductVO> pro_list(Criteria cri) {
		// TODO Auto-generated method stub
		return adProductMapper.pro_list(cri);
	}

	@Override
	public int getTotelCount(Criteria cri) {
		// TODO Auto-generated method stub
		return adProductMapper.getTotelCount(cri);
	}

	@Override
	public void pro_checked_modify1(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pro_num_arr.size(); i++) {
			adProductMapper.pro_checked_modify1(pro_num_arr.get(i), pro_price_arr.get(i), pro_buy_arr.get(i));
		}
	}

	@Override
	public void delete(Integer pro_num) {
		// TODO Auto-generated method stub
		adProductMapper.delete(pro_num);
	}

	@Override
	public ProductVO pro_edit_page(Integer pro_num) {
		// TODO Auto-generated method stub
		return adProductMapper.pro_edit_page(pro_num);
	}

	@Override
	public void pro_edit(ProductVO vo) {
		// TODO Auto-generated method stub
		adProductMapper.pro_edit(vo);
	}
	
}
