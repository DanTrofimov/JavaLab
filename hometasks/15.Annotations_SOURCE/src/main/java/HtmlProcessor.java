import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".ftl";
            Path out = Paths.get(path);

            element.getEnclosedElements().get(0).getAnnotation(HtmlInput.class);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                writer.write("<form action='" + annotation.action() + "' method='" + annotation.method() + "'/>");
                writer.newLine();

                for (Element elementIn : element.getEnclosedElements()) {
                    HtmlInput inputAnnotation = elementIn.getAnnotation(HtmlInput.class);
                    if (inputAnnotation != null) {
                        writer.write("<input type='" + inputAnnotation.type() + "' name='" + inputAnnotation.name() + "' placeholder='" + inputAnnotation .placeholder() + "'/>");
                        writer.newLine();
                    }
                }

                writer.write("</form>");
                writer.newLine();

                writer.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return true;
    }
}
