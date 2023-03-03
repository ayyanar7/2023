package com.ag.bta.main.models.home;

import com.ag.bta.main.wavelayout.models.ItemCard;

import java.util.ArrayList;
import java.util.HashMap;

public class Container {
    HashMap<String, PagerContent> allPages = new  HashMap<String, PagerContent>();

    public HashMap<String, PagerContent> getAllPages() {
        return allPages;
    }

    public void setAllPages(HashMap<String, PagerContent> allPages) {
        this.allPages = allPages;
    }

    public void addPage(String key, PagerContent pacont) {
        this.allPages.put(key, pacont);
    }

    public  Container generatePageContents(Home hom){
        Container contain =  new Container();
        for (Tab tab: hom.getTabs()){
            for (SubTab stab: tab.getSubTabs()){
                if(stab.isSubSubTabExist()){
                    for (SubSubTab sstab: stab.getSubSubTab()){
                        contain.addPage( sstab.getPagerContent(), generatePageContent(0,new int[]{0}));
                    }
                }else{
                    contain.addPage( stab.getPagerContent(), generatePageContent(0,new int[]{0}));
                }
            }
        }
        return contain;
    }

    private PagerContent generatePageContent(int pageType, int[] secTypeList) {
        PagerContent page = new PagerContent();
        page.setPageType(pageType);
        ArrayList<SectionBar> secList = new ArrayList<SectionBar>();

        for (int sectionType : secTypeList) {
            SectionBar sec = new SectionBar();
            sec.setItemsType(sectionType);
            sec.setItemslist(generateItem(sectionType));

            secList.add(sec);
        }

        page.setSectionlist(secList);
        return page;

    }

    private ArrayList<ItemCard>  generateItem(int type){
        ArrayList<ItemCard> itemlist = new ArrayList<ItemCard>();

        for (int i = 0; i < 10; i++) {
            ItemCard itm = new ItemCard("Item "+i, "Description ", type);
            itemlist.add(itm);
        }
        return itemlist;
    }

}
