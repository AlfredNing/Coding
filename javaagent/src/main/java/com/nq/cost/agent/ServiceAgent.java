package com.nq.cost.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * ClassFileTransformer作用是在类加载到 JVM 时，允许用户自定义类转换的逻辑，从而可以修改类的字节码。
 */

public class ServiceAgent implements ClassFileTransformer {

    /**
     * @param loader              定义了加载类的类加载器。
     * @param className           正在转换的类的二进制名称。
     * @param classBeingRedefined 果这个转换是重定义操作的一部分，这个参数将指向正在被重定义的类；如果是首次加载类，则此参数为 null。
     * @param protectionDomain    类的保护域。
     * @param classfileBuffer     类的字节码，可以被修改。
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!"com/nq/cost/agent/UserServiceImpl".equals(className)) {
            return null;
        }
        ClassPool classPool = new ClassPool();
        classPool.appendSystemPath();
        try {
            CtClass ctClass = classPool.get("com.nq.cost.agent.UserServiceImpl");
            CtMethod method = ctClass.getDeclaredMethods("findUser")[0];
//            method.addLocalVariable("begin", CtClass.longType);
//            method.insertBefore("begin = System.currentTimeMillis();");
//            method.insertAfter("long end = System.currentTimeMillis();\n" +
//                    "            System.out.println(end-begin);");

            // 放在finally中执行
            CtMethod newMethod = CtNewMethod.copy(method, ctClass, null);
            newMethod.setName(newMethod.getName() + "$agent");
            ctClass.addMethod(newMethod);

            method.setBody("{  long begin = System.currentTimeMillis();\n" +
                    "            try {\n" +
                    "                findUser$agent($$);\n" +
                    "            } finally {\n" +
                    "                long end = System.currentTimeMillis();\n" +
                    "                System.out.println(\"end-begin:\" + (end - begin));\n" +
                    "            } }");
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
