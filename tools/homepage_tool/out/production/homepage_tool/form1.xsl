<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="xml" indent="yes"/>

   <xsl:template match="root">      
	<root>
	  <xsl:apply-templates select="table"/>
	</root>
   </xsl:template>

   <xsl:template match="table">
    <table>
	<name><xsl:value-of select="@name"/></name>
	<alias><xsl:value-of select="@alias"/></alias>
	<componentid><xsl:value-of select="@componentid"/></componentid>
	<codecontinuous><xsl:value-of select="@codecontinuous"/></codecontinuous>
	<description><xsl:value-of select="@description"/></description>
       <xsl:apply-templates select="fields"/>
    </table>
   </xsl:template>

   <xsl:template match="fields">
    <fields>
     <xsl:apply-templates select="field"/>	
    </fields>
   </xsl:template>

    <xsl:template match="field">
    <field>
	<name><xsl:value-of select="@name"/></name>
	<label><xsl:value-of select="@label"/></label>
	<type><xsl:value-of select="@type"/></type>
	<length><xsl:value-of select="@length"/></length>
	<dec><xsl:value-of select="@dec"/></dec>
	<default><xsl:value-of select="@default"/></default>
	<required><xsl:value-of select="@required"/></required>
	<description><xsl:value-of select="@description"/></description>     
    </field>
   </xsl:template>

</xsl:stylesheet>