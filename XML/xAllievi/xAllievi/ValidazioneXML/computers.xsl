<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>computers.xsl</title>
            </head>
            <body>
                <h2>Lista Computer</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Tastiera</th>
                        <th>Mouse</th>
                        <th>Monitor</th>
                    </tr>
                    <xsl:for-each select="listaComputer/computer">

                        <tr>
                            <td>
                                <xsl:value-of select="keyboard"/>
                            </td>
                            <td>
                                <xsl:value-of select="mouse"/>
                            </td>
                            <td>
                                <xsl:value-of select="monitor"/>
                            </td>
                        </tr>

                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
