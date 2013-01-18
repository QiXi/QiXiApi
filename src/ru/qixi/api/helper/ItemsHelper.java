package ru.qixi.api.helper;



public class ItemsHelper {
	
	private int itemIndex;
	private int itemCount;
	
	private int listIndex;
	private int listCount;
	private int itemCountOnList;
	
	private boolean enabled;
	
	private boolean enabledNextItem;
	private boolean enabledPrevItem;	
	private boolean enabledNextList;
	private boolean enabledPrevList;	
	
	
	public ItemsHelper(int count, int countOnList)
	{
		itemCount = count;
		itemCountOnList = countOnList;
		listCount = count/itemCountOnList;
		if (count%itemCountOnList!=0){
			listCount++;
		}		
		enabled = (count<1)?false:true;	
		updateEnabledItem();
		updateEnabledList();
	}	
	
	
	public int getIndexItem()
	{
		return itemIndex;
	}
	
	
	public void setIndexItem(int value)
	{
		if (value>=0 && value<itemCount){
			itemIndex=value;
			updateItem();
		}
	}
	
	
	public int getIndexList()
	{
		return listIndex;
	}
	
	
	public int getIndexOnList()
	{
		return listIndex*itemCountOnList;
	}
	
	
	public int getListCount()
	{
		return listCount;
	}
	
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	
	public boolean isEnabledPrevItem()
	{
		return enabledPrevItem;
	}
	
	
	public boolean isEnabledNextItem()
	{
		return enabledNextItem;
	}
	
	
	public boolean isEnabledPrevList()
	{
		return enabledPrevList;
	}
	
	
	public boolean isEnabledNextList()
	{
		return enabledNextList;
	}
	
	
	public void nextList()
	{
		if (!enabled) return;
		listIndex++;
		if (listIndex>listCount-1) {
			listIndex=listCount-1;
			return;
		}
		updateList();
	}
	
	
	public void prevList()
	{
		if (!enabled) return;
		listIndex--;
		if (listIndex<0) {
			listIndex=0;
			return;
		}
		updateList();
	}		
	
	
	public void nextItem()
	{
		if (!enabled) return;
		itemIndex++;
		if (itemIndex>itemCount-1) {
			itemIndex=itemCount-1;
			return;
		}
		updateItem();
	}
	
	
	public void prevItem()
	{
		if (!enabled) return;
		itemIndex--;
		if (itemIndex<0) {
			itemIndex=0;
			return;
		}
		updateItem();
	}
	
	
	protected void updateList()
	{
		updateEnabledList();
	}
	
	
	private void updateEnabledList()
	{
		if (listIndex<1) {
			enabledPrevList=false;
		} else {
			enabledPrevList=true;
		}
		if (listIndex>=listCount-1) {
			enabledNextList=false;
		} else {
			enabledNextList=true;
		}
	}
	
	
	protected void updateItem()
	{
		updateEnabledItem();
	}
	
	
	private void updateEnabledItem()
	{
		if (itemIndex<1) {
			enabledPrevItem=false;
		} else {
			enabledPrevItem=true;
		}
		if (itemIndex>=itemCount-1) {
			enabledNextItem=false;
		} else {
			enabledNextItem=true;
		}
	}	
	
}
