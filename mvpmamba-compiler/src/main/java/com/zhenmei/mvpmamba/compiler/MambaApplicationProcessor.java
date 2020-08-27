package com.zhenmei.mvpmamba.compiler;

import com.google.auto.service.AutoService;
import com.zhenmei.mvpmamba.annotation.AutoActivityModule;
import com.zhenmei.mvpmamba.annotation.MambaApplication;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.zhenmei.mvpmamba.annotation.MambaApplication"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MambaApplicationProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Types typeUtils;
    private Messager messager;
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
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(MambaApplication.class);

        List<Element> list = new ArrayList(elements);
        // 通过类节点获取包节点（全路径：com.example.annotation）
        String packageName = elementUtils.getPackageOf(list.get(0)).getQualifiedName().toString();
        // 获取简单类名
        String className = list.get(0).getSimpleName().toString();

        String finalClassName = className + "$$AppComponent";
        try {
            JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + finalClassName);
            Writer writer = sourceFile.openWriter();
            // 设置包名
            writer.write("package " + packageName + ";\n");
            writer.write("import " + "javax.inject.Singleton;" + ";\n");
            writer.write("import " + "dagger.BindsInstance;" + ";\n");
            writer.write("import " + "dagger.Component;" + ";\n");
            writer.write("import " + "dagger.android.support.AndroidSupportInjectionModule;" + ";\n");
            writer.write("import " + packageName + "." + className + ";\n");


            writer.write("@Singleton\n" +
                    "@Component(modules = {\n" +
                    "        AndroidSupportInjectionModule.class,\n" +
                    "        BuildersModule.class})\n");


            writer.write("public interface AppComponent {\n" +
                    "    @Component.Builder\n" +
                    "    interface Builder {\n" +
                    "\n" +
                    "        AppComponent build();\n");


            writer.write("        @BindsInstance\n" +
                    "        Builder application(" + className +
                    " application);\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    void inject(" + className +
                    " app);\n" +
                    "\n" +
                    "}");

            messager.printMessage(Diagnostic.Kind.NOTE, "MambaApplication-output:" + finalClassName);


            // 最后结束别忘了
            writer.close();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return true;

    }
}
