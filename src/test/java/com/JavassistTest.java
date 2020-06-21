package com;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class JavassistTest {

	@Test
	public void case1() {
		
	}
	
//	@Test
//	public void genClass() {
//		//读取
//        ClassReader classReader = new ClassReader("meituan/bytecode/asm/Base");
//        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        //处理
//        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
//        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
//        byte[] data = classWriter.toByteArray();
//        //输出
//        File f = new File("operation-server/target/classes/meituan/bytecode/asm/Base.class");
//        FileOutputStream fout = new FileOutputStream(f);
//        fout.write(data);
//        fout.close();
//        System.out.println("now generator cc success!!!!!");
//	}
	
}
