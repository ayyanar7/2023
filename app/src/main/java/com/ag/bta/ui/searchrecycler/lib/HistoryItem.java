package com.ag.bta.ui.searchrecycler.lib;

class HistoryItem extends ListItem {
    static final int TYPE = 1;
    private String term;

    HistoryItem(String term) {
        super();
        this.term = term;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    String getTerm() {
        return term;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HistoryItem) {
            return ((HistoryItem) obj).term.equals(term);
        }
        return false;
    }
}
