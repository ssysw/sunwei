package com.briup.estore.dao;

import java.util.List;

import com.briup.estore.bean.Line;

public interface ILineDao  {
	public void savaLine(Line line);
	public void deleteLineByOrderId(long orderId);
	public List<Line> findLineByOrderId(long orderId);
}  
