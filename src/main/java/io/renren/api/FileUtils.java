package io.renren.api;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 输入项目的工程名称，及要上次的文件数据组对象，在当前项目对应的webapps目录下建立一个pics文件夹，
 * 目前只支持单文件的上传，在上传过程中需要压缩文件
 * @author Administrator
 *
 */

public class FileUtils {
	public String uploadPic(MultipartFile[] files,HttpServletRequest request,String projectName) throws IllegalStateException, IOException{
    	
		String url = "";
		//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
		String path = request.getSession().getServletContext().getRealPath("upload");
		
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		
		System.out.println("basePath="+basePath);
		String filePath = path.split("renren-web")[0]+"pics";
		System.out.println("servletPath="+path);
		
		//先判断文件夹是否存在，如果不存在就创建一个，存在不管
		File dirFile  = new File(filePath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		for(MultipartFile file : files){
			String fileName = file.getOriginalFilename();
			System.out.println("fileName="+fileName);
			String format=fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println("fileNameFormat="+format);
			//获取UUID
			UUID id=UUID.randomUUID();
			String uuid = id.toString().replace("-", "");
			//拼接一个完整的字符串
			String _file = filePath+"\\"+uuid+"."+format;
			System.out.println("_file="+_file);
			//在服务器端保存起来
			file.transferTo(new File(_file));
			//将文件的信息保存在数据库中
			
			url =basePath+ "/pics/"+uuid+"."+format;
			System.out.println("url="+url);
		}
		
        return url;
    }
	
	
	
	
	public static Map<String,Object> uploadPics(MultipartFile[] files,HttpServletRequest request,String projectName,String default_original_url,String default_sub_url) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			String url = default_original_url;
			String original_url=default_sub_url;
			//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
			String path = request.getSession().getServletContext().getRealPath(projectName);
			System.out.println("path="+path);
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort();
			
			System.out.println("basePath="+basePath);
			//在服务器上的物理地址
			String filePath = path.split(projectName)[0]+"pics";

			//先判断文件夹是否存在，如果不存在就创建一个，存在不管
			File dirFile  = new File(filePath);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			System.out.println("length="+files.length);

				for(MultipartFile file : files){
					String fileName = file.getOriginalFilename();
					//如果文件名称为空，直接返回，不要做上传动作
					if("".equals(fileName)||fileName==null){
						map.put("original_url", original_url);
						map.put("sub_url", url);
						return map;
					}
					System.out.println("fileName="+fileName);
					String format=fileName.substring(fileName.lastIndexOf(".")+1);
					System.out.println("fileNameFormat="+format);
					//获取UUID
					UUID id=UUID.randomUUID();
					String uuid = id.toString().replace("-", "");
					//拼接一个完整的字符串
					String _file = filePath+"\\"+uuid+"."+format;
					System.out.println("_file="+_file);
					//在服务器端保存起来
					file.transferTo(new File(_file));
				
					String s = FileUtils.createThumbPic(new File(_file), filePath);
					System.out.println("sfile_name="+s);
					//这个路径是缩略图的完整的地址
					url =basePath+ "/pics/"+uuid+"_sub."+format;
					original_url =basePath+ "/pics/"+uuid+"."+format;
					System.out.println("original_url="+original_url);
					System.out.println("url="+url);
					
					map.put("original_url", original_url);
					map.put("sub_url", url);
				}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
    }
	
	
	
	/**
	* 压缩图片 文件名为原图名_sub
	* 
	* @param fileName
	* @return
	* @throws IOException
	*/
	public static String createThumbPic(File file,String realPath) throws IOException {
	// java.io.File pFile = new java.io.File(file);

	String path = realPath+"/"+file.getName().replace(".", "_sub.");
	String fileName = file.getName();
	java.io.File fo = new java.io.File(path); // 将要转换出的小图文件
	int nw = 150;
	AffineTransform transform = new AffineTransform();
	BufferedImage buffer = ImageIO.read(file); // 读取图片
	int width = buffer.getWidth();
	int height = buffer.getHeight();
	int nh = (nw * height) / width;
	double sx = (double) nw / width;
	double sy = (double) nh / height;
	transform.setToScale(sx, sy);
	AffineTransformOp ato = new AffineTransformOp(transform, null);
	BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
	ato.filter(buffer, bid);
	String type = fileName.substring(fileName.indexOf('.') + 1, fileName.length());
	// System.out.println("type = " + type);
	ImageIO.write(bid, type, fo);
	return fo.getName();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
