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
	<xsl:element name="table">
	      <xsl:attribute name="name"><xsl:value-of select="@alias"/></xsl:attribute>
	      <xsl:attribute name="cname"><xsl:value-of select="@name"/></xsl:attribute>
	      <xsl:attribute name="fathertablename"><xsl:value-of select="@fathertablename"/></xsl:attribute>
	      <xsl:attribute name="mode"><xsl:if test="not(@mode)">0</xsl:if><xsl:if test="@mode=''">0</xsl:if><xsl:value-of select="@mode"/></xsl:attribute>
	      <xsl:attribute name="keyfieldname"><xsl:value-of select="@keyfieldname"/></xsl:attribute>
          <xsl:apply-templates select="fields"/>
	</xsl:element>
   </xsl:template>

   <xsl:template match="fields">
     <xsl:apply-templates select="field"/>	
   </xsl:template>

    <xsl:template match="field">
    <field>
	<name><xsl:value-of select="@name"/></name>
	<cname><xsl:value-of select="@label"/></cname>
	<type><xsl:value-of select="@worktype"/></type>
	<forignkey><xsl:value-of select="@forignkey"/></forignkey>
	<fieldType><xsl:value-of select="@type"/></fieldType>
    </field>
   </xsl:template>

</xsl:stylesheet>