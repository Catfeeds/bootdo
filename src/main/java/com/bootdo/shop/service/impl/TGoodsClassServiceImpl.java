package com.bootdo.shop.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.domain.DeptDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.shop.dao.TGoodsClassDao;
import com.bootdo.shop.domain.TGoodsClassDO;
import com.bootdo.shop.service.TGoodsClassService;



@Service
public class TGoodsClassServiceImpl implements TGoodsClassService {
	@Autowired
	private TGoodsClassDao tGoodsClassDao;
	
	@Override
	public TGoodsClassDO get(Long id){
		return tGoodsClassDao.get(id);
	}
	
	@Override
	public List<TGoodsClassDO> list(Map<String, Object> map){
		return tGoodsClassDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tGoodsClassDao.count(map);
	}
	
	@Override
	public int save(TGoodsClassDO tGoodsClass){
		return tGoodsClassDao.save(tGoodsClass);
	}
	
	@Override
	public int update(TGoodsClassDO tGoodsClass){
		return tGoodsClassDao.update(tGoodsClass);
	}
	
	@Override
	public int remove(Long id){
		return tGoodsClassDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tGoodsClassDao.batchRemove(ids);
	}
	@Override
	public Tree<TGoodsClassDO> getTree() {
		List<Tree<TGoodsClassDO>> trees = new ArrayList<Tree<TGoodsClassDO>>();
		List<TGoodsClassDO> SysDepts = tGoodsClassDao.list(new HashMap<String,Object>());
		for (TGoodsClassDO gc : SysDepts) {
			Tree<TGoodsClassDO> tree = new Tree<TGoodsClassDO>();
			tree.setId(gc.getId().toString());
			tree.setParentId(gc.getPid().toString());
			tree.setText(gc.getName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<TGoodsClassDO> t = BuildTree.build(trees);
		return t;
	}

}
