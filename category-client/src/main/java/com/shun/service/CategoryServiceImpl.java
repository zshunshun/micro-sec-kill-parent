package com.shun.service;

import com.shun.dao.CategoryDao;
import com.shun.entity.Category;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public Map findByPage(Integer page, Integer rows) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        Example example = new Example(Category.class);
        example.createCriteria().andIsNull("parentId");
        List<Category> categories = categoryDao.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
        int records = categoryDao.selectCountByExample(example);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",categories);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Map edit(Category category) {
        Map map = new HashMap();
        categoryDao.updateByPrimaryKeySelective(category);
        map.put("status",200);
        return map;
    }

    @Override
    public Map add(String name) {
        Map map = new HashMap();
        Category category = new Category();
        category.setName(name);
        Category reCategory = categoryDao.selectOne(category);
        if(reCategory==null){
            categoryDao.insertSelective(category);
            map.put("status",200);
        }else{
            map.put("status",500);
        }
        return map;
    }

    @Override
    public Map del(String[] id) {
        Map map = new HashMap();
        List list = new ArrayList();
        for (int i = 0; i < id.length; i++) {
            list.add(Integer.valueOf(id[i]));
        }
        categoryDao.deleteByIdList(list);
        map.put("status",200);
        return map;
    }

    @Override
    public Map findByParentId(Integer page,Integer rows,Integer parentId) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",parentId);
        List<Category> categories = categoryDao.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
        int records = categoryDao.selectCountByExample(example);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",categories);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Map addChild(Integer parentId, String name) {
        Map map = new HashMap();
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(name);
        Category reCategory = categoryDao.selectOne(category);
        if(reCategory==null){
            categoryDao.insertSelective(category);
            map.put("status",200);
        }else{
            map.put("status",500);
        }
        return map;
    }

    @Override
    public List findAllParents() {
        Example example = new Example(Category.class);
        example.createCriteria().andIsNull("parentId");
        return categoryDao.selectByExample(example);
    }

    @Override
    public Map findById(Integer id) {
        Map map = new HashMap();
        Category category = categoryDao.selectOne(new Category(id, null, null));
        if(category==null){
            map.put("status",500);
        }else{
            map.put("status",200);
            map.put("category",category);
        }
        return map;
    }

    @Override
    public Map findAllByParentId(Integer parentId) {
        Map map = new HashMap();
        List<Category> category = categoryDao.select(new Category(null, null, parentId));
        if(category==null){
            map.put("status",500);
        }else{
            map.put("status",200);
            map.put("category",category);
        }
        return map;
    }
}
