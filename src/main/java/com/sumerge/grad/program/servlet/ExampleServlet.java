package com.sumerge.grad.program.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ahmed Anwar
 */
@WebServlet(urlPatterns = "hello")
public class ExampleServlet extends HttpServlet
{
	private static final long serialVersionUID = 4347254460337683196L;
	private static Integer counter = 0;
	private Integer number = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("Entering doGet for " + number);
		resp.getWriter().println("Hello World Get!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("Entering doPost for " + number);
		resp.getWriter().println("Hello World! Post");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("Entering doPut for " + number);
		resp.getWriter().println("Hello World! Put");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("Entering doDelete for " + number);
		resp.getWriter().println("Hello World Delete!");
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("Entering doOptions for " + number);
		resp.getWriter().println("Hello World Options!");
	}

	@Override
	public void destroy()
	{
		System.out.println("Entering destroy for " + number);
		super.destroy();
	}

	@Override
	public void init() throws ServletException
	{
		counter++;
		this.number = counter;
		System.out.println("Entering init for " + number);
		super.init();
	}
}
