package com.zhenmei.mvpmamba.compiler;

import com.google.auto.service.AutoService;
import com.zhenmei.mvpmamba.annotation.AutoActivityModule;
import com.zhenmei.mvpmamba.annotation.MambaModel;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

// AutoService则是固定的写法，加个注解即可
// 通过auto-service中的@AutoService可以自动生成AutoService注解处理器，用来注册
// 用来生成 META-INF/services/javax.annotation.processing.Processor 文件
@AutoService(Processor.class)
// 允许/支持的注解类型，让注解处理器处理（新增annotation module）
@SupportedAnnotationTypes({"com.zhenmei.mvpmamba.annotation.MambaModel"})
// 指定JDK编译版本
@SupportedSourceVersion(SourceVersion.RELEASE_8)
// 注解处理器接收的参数
public class MambaModelProcessor extends AbstractProcessor {

    // 操作Element工具类 (类、函数、属性都是Element)
    private Elements elementUtils;
    // type(类信息)工具类，包含用于操作TypeMirror的工具方法
    private Types typeUtils;
    // Messager用来报告错误，警告和其他提示信息
    private Messager messager;
    // 文件生成器 类/资源，Filter用来创建新的源文件，class文件以及辅助文件
    private Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set.isEmpty()) return false;

        // 获取所有带ARouter注解的 类节点
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(MambaModel.class);
        messager.printMessage(Diagnostic.Kind.NOTE, "输出：：：：：：：：：");

        List<Element> list = new ArrayList(elements);


        // 通过类节点获取包节点（全路径：com.example.annotation）
        String packageName = elementUtils.getPackageOf(list.get(0)).getQualifiedName().toString();
        // 创建一个新的源文件（Class），并返回一个对象以允许写入它
        try {
            // 最终想生成的类文件名
            String finalClassName = "Mamba$$ActivityModel";

            JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + finalClassName);

            // 定义Writer对象，开启写入
            Writer writer = sourceFile.openWriter();

            // 设置包名
            writer.write("package " + packageName + ";\n");

            writer.write("import dagger.Module;\n");
            writer.write("import dagger.Provides;\n");
            for (Element element : elements) {
                MambaModel mambaModel = element.getAnnotation(MambaModel.class);
                String packagePath = elementUtils.getPackageOf(element).getQualifiedName().toString();
                String className = element.getSimpleName().toString();

                writer.write("import " + packagePath + "." + className + ";\n");
                writer.write("import " + getClassFromAnnotation(element) + ";\n");

                messager.printMessage(Diagnostic.Kind.NOTE, "output:" + getClassFromAnnotation(element));


            }


//            // 遍历所有类节点
//            for (Element element : elements) {
//                // 获取简单类名
//                String className = element.getSimpleName().toString();
//
//                writer.write("public class " + finalClassName + " {\n");
//
//                messager.printMessage(Diagnostic.Kind.NOTE, "输出:" + className);
//
//                writer.write("public static Class<?> findTargetClass(String path) {\n");
//
//                // 获取类之上@ARouter注解的path值
//                AutoActivityModule aRouter = element.getAnnotation(AutoActivityModule.class);
//
//                writer.write("if (path.equals(\"" + aRouter.toString() + "\")) {\n");
//
//                writer.write("return " + className + ".class;\n}\n");
//
//                writer.write("return null;\n");
//
//                writer.write("}\n}");
//                // 最后结束别忘了
//
//
//            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }


    private String getClassFromAnnotation(Element key) {
        List<? extends AnnotationMirror> annotationMirrors = key.getAnnotationMirrors();
        for (AnnotationMirror annotationMirror : annotationMirrors) {
            if (MambaModel.class.getName().equals(annotationMirror.getAnnotationType().toString())) {
                Set<? extends ExecutableElement> keySet = annotationMirror.getElementValues().keySet();
                for (ExecutableElement executableElement : keySet) {
                    if (Objects.equals(executableElement.getSimpleName().toString(), "modelClass")) {
                        return annotationMirror.getElementValues().get(executableElement).getValue().toString();
                    }
                }
            }
        }
        return null;
    }
}
