package com.mn.pp.TestUnit;

import org.junit.Test;

/**
 * Created by Administrator on 2017/2/9 0009.
 */
public class GenealogyTest extends AbstractSpringWithJunitTestRunner{

//    @Autowired
//    @Qualifier("genealogyDao")
//    IGenealogyDao iGenealogyDao;

    @Test
    @Override
    public void find() {//201702092209280000020628842693
    // 201702092209270000010412325074
//        List<Genealogy> genealogies = iGenealogyDao.findAll();
//        System.out.println(genealogies.size());
    }

    @Test
    @Override
    public void update() {
        //iGenealogyDao.addFamilyMember("201702092209280000020628842693",StringUtils.getId());
        //List<Genealogy> genealogies = iGenealogyDao.findAll();
        //System.out.println(genealogies.size());
//        iGenealogyDao.deleteFamilyMember("201702092209280000020628842693", "201702092209270000010412325074");
//        List<Genealogy> genealogies = iGenealogyDao.findAll();
//        System.out.println(genealogies.size());
    }

    @Test
    @Override
    public void save() {
//        List<String> list = new ArrayList<>();
//        list.add(StringUtils.getId());
//        list.add(StringUtils.getId());
//        genealogy.setUid(StringUtils.getId());
//        genealogy.setAdminId(StringUtils.getId());
//        genealogy.setFmIdList(list);
//        iGenealogyDao.save(genealogy); Genealogy genealogy =new Genealogy();

    }

    @Override
    public void delete() {

    }
}
