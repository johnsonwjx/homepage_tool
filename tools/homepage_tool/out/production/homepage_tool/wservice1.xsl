<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="root">
		<InitDataList><InitData name="DDATA">
			<xsl:apply-templates select="table" />
		</InitData>
		<InitData name="RDATA_03"/>
		</InitDataList>
	</xsl:template>
	<xsl:template match="table">
		<Table>
			<TABLE_NAME>
				<xsl:value-of select="@alias" />
			</TABLE_NAME>
			<TABLE_DESC>
				<xsl:value-of select="@name" />
			</TABLE_DESC>
			<xsl:apply-templates select="indexs" />
			<xsl:apply-templates select="fields" />
		</Table>
	</xsl:template>
	<xsl:template match="indexs">
		<indexs>
			<xsl:apply-templates select="index" />
		</indexs>
	</xsl:template>
	<xsl:template match="index">
		<index>
			<name>
				<xsl:value-of select="@name" />
			</name>
			<fields>
				<xsl:value-of select="@fields" />
			</fields>
			<type>
				<xsl:value-of select="@type" />
			</type>
		</index>
	</xsl:template>
	<xsl:template match="fields">
		
			<xsl:apply-templates select="field" />
		
	</xsl:template>
	<xsl:template match="field">
		<Field>
			<Para name="FIELD_NAME">
				<xsl:value-of select="@name" />
			</Para>
			<Para name="FIELD_DESC">
				<xsl:value-of select="@label" />
			</Para>
			<Para name="FIELD_TYPE">
				<xsl:value-of select="@type" />
			</Para>
			<Para name="FIELD_LENGTH">
				<xsl:value-of select="@length" />
			</Para>
			<Para name="FIELD_DEC">
				<xsl:value-of select="@dec" />
			</Para>
			<Para name="FIELD_NULL">
				<xsl:value-of select="@null" />
			</Para>
			<Para name="FIELD_DEFAULT">
				<xsl:value-of select="@default" />
			</Para>
		</Field>
	</xsl:template>
</xsl:stylesheet>