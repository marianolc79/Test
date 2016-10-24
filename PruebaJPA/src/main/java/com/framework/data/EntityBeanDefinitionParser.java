/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import java.util.HashMap;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author mariano
 */
public class EntityBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return MyEntityInfo.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        if (!element.hasAttribute("id")) {
            throw new IllegalStateException("Entidad sin id");
        }
        bean.addPropertyValue("id", element.getAttribute("id"));

        NodeList nodeList = element.getChildNodes();
        HashMap<String, MyQuery> map = new HashMap<String, MyQuery>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                Element child = (Element) nodeList.item(i);
                if (child.getAttribute("id") == null) {
                    throw new IllegalStateException("Query sin id");
                }
                MyQuery myQuery = map.get(child.getAttribute("id"));
                if (myQuery == null) {
                    myQuery = new MyQuery(child.getAttribute("id"));
                    map.put(myQuery.getId(), myQuery);
                }
                String name = child.getLocalName();
                if ("namedQuery".equals(name)) {
                    myQuery = getMyQuery(child, myQuery);
                }
                if ("queryOrder".equals(name)) {
                    myQuery = getMyQueryOrder(child, myQuery);
                }
            }
        }
        bean.addPropertyValue("mapping", map);

    }

    private MyQuery getMyQuery(Element child, MyQuery myQuery) {
        if (child.getAttribute("id") == null) {
            throw new IllegalStateException("Query sin id");
        }
        if (myQuery == null) {
            myQuery = new MyQuery(child.getAttribute("id"));
        }
        if (child.getAttribute("type") != null && "native".equals(child.getAttribute("type"))) {
            myQuery.setQueryType(MyQuery.Type.NATIVE);
        }
        NodeList nodeList = child.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getTextContent() != null && !"".equals(nodeList.item(i).getTextContent().trim())) {
                String queryText = nodeList.item(i).getTextContent().trim();
                myQuery.setQueryText(queryText);
            }
        }
        return myQuery;
    }

    private MyQuery getMyQueryOrder(Element child, MyQuery myQuery) {
        if (child.getAttribute("id") == null) {
            throw new IllegalStateException("Query sin id");
        }
        if (myQuery == null) {
            myQuery = new MyQuery(child.getAttribute("id"));
        }
        NodeList nodeList = child.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                Element child1 = (Element) nodeList.item(i);
                if ("column".equals(child1.getLocalName())) {
                    String id = child1.getAttribute("id");
                    String columnText = child1.getAttribute("columnText");
                    myQuery.addQueryOrderMapping(id, columnText);
                }
            }
        }
        return myQuery;
    }
}
