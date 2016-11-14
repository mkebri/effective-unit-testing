/*
 * Copyright 2007 Lasse Koskela.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jsptest.acceptance.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import net.sf.jsptest.JspTestCase;

/**
 * @author Lasse Koskela
 * @author Meinert Schwartau (scwar32)
 */
public class TestMockingTaglibs extends JspTestCase {

    protected String getJspPackageName() {
        return "cust.om.tags";
    }

    protected String getWebRoot() {
        return "src/test/resources/websrc";
    }

    /**
     * This taglib class gets to replace the real thing (&lt;c:_/&gt;).
     */
    public static class MockCustomTag extends CustomTag {

        public int doAfterBody() throws JspException {
            try {
                JspWriter out = pageContext.getOut();
                out.println("<pre>");
                out.println("This content is coming from the mock CustomTag" + " for timezone "
                        + timezone);
                out.println("</pre>");
            } catch (Exception e) {
                throw new JspException(e);
            }
            return TagSupport.EVAL_PAGE;
        }
    }

    /**
     * This taglib class gets to replace the real thing (&lt;c:out/&gt;).
     */
    public static class MockCustomOutTag extends CustomTag {

        public void setValue(String value) {
        }

        public int doStartTag() throws JspException {
            try {
                JspWriter out = pageContext.getOut();
                out.print("replaced");
            } catch (Exception e) {
                throw new JspException(e);
            }
            return TagSupport.SKIP_BODY;
        }

        public int doEndTag() throws JspException {
            return TagSupport.EVAL_PAGE;
        }
    }

    public void testSubstitutingCustomTaglibs() throws Exception {
        substituteTaglib("custom", MockCustomTag.class);
        get("/taglibs/custom-taglib.jsp");
        output().shouldContain("This content is coming from the mock CustomTag");
    }

    public void testSubstitutingSpecificTagWithinTagLibrary() throws Exception {
        substituteTag("c", "out", MockCustomOutTag.class);
        get("/taglibs/standard-taglibs.jsp");
        output().shouldNotContain("loop-1");
        output().shouldContain("replaced-replaced");
    }
}
