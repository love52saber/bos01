package cn.itcast.controller.aop;

import cn.itcast.controller.exception.ParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

@Component
@Aspect
@Order(2)
public class ParamValidateAop {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static ExecutableValidator validator = factory.getValidator().forExecutables();

    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("execution(* cn.itcast.controller.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint pj) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("");
        //  获得切入目标对象
        Object target = pj.getThis();
        // 获得切入方法参数
        Object[] args = pj.getArgs();
        // 获得切入的方法
        MethodSignature methodSignature = (MethodSignature) pj.getSignature();
        Method method = methodSignature.getMethod();

        //参数：目标对象，目标方法，目标参数
        Set<ConstraintViolation<Object>> validResult = validator.validateParameters(target, method, args);

        if (!validResult.isEmpty()) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
            for (ConstraintViolation<Object> violation : validResult) {
                //获取路径，
                PathImpl propertyPath = (PathImpl) violation.getPropertyPath();
                //获得当前节点
                NodeImpl leafNode = propertyPath.getLeafNode();
                //获得当前节点类型，
                String leafNodeKind = leafNode.getKind().toString();
                //出错节点有可能是pojo的属性
                String msg = "";
                int violatedParameterIndex = leafNodeKind.equals("PROPERTY") ? leafNode.getParent().getParameterIndex() : leafNode.getParameterIndex();
                //获取参数名称
                String violatedParameterName = parameterNames[violatedParameterIndex];
                if (leafNodeKind.equals("PROPERTY")) {
                    msg = "参数异常--" + leafNode.getName() + "-" + violation.getMessage().toString();
                } else {
                    msg = "参数异常--" + violatedParameterName + "-" + violation.getMessage().toString();
                }
                throw new ParamException(msg);
            }
        }
    }
}
