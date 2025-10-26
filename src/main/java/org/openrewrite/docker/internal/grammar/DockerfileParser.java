/*
 * Copyright 2025 the original author or authors.
 * <p>
 * Licensed under the Moderne Source Available License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://docs.moderne.io/licensing/moderne-source-available-license
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated from /home/tim/Documents/workspace/openrewrite/rewrite-docker/src/main/antlr/DockerfileParser.g4 by ANTLR 4.13.2
package org.openrewrite.docker.internal.grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DockerfileParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PARSER_DIRECTIVE=1, COMMENT=2, FROM=3, RUN=4, CMD=5, LABEL=6, EXPOSE=7,
		ENV=8, ADD=9, COPY=10, ENTRYPOINT=11, VOLUME=12, USER=13, WORKDIR=14,
		ARG=15, ONBUILD=16, STOPSIGNAL=17, HEALTHCHECK=18, SHELL=19, MAINTAINER=20,
		AS=21, HEREDOC_START=22, LINE_CONTINUATION=23, LBRACKET=24, RBRACKET=25,
		EQUALS=26, DASH_DASH=27, DOUBLE_QUOTED_STRING=28, SINGLE_QUOTED_STRING=29,
		ENV_VAR=30, UNQUOTED_TEXT=31, WS=32, NEWLINE=33, JSON_COMMA=34, JSON_RBRACKET=35,
		JSON_STRING=36, JSON_WS=37;
	public static final int
		RULE_dockerfile = 0, RULE_parserDirective = 1, RULE_instruction = 2, RULE_fromInstruction = 3,
		RULE_runInstruction = 4, RULE_cmdInstruction = 5, RULE_labelInstruction = 6,
		RULE_exposeInstruction = 7, RULE_envInstruction = 8, RULE_addInstruction = 9,
		RULE_copyInstruction = 10, RULE_entrypointInstruction = 11, RULE_volumeInstruction = 12,
		RULE_userInstruction = 13, RULE_workdirInstruction = 14, RULE_argInstruction = 15,
		RULE_onbuildInstruction = 16, RULE_stopsignalInstruction = 17, RULE_healthcheckInstruction = 18,
		RULE_shellInstruction = 19, RULE_maintainerInstruction = 20, RULE_flags = 21,
		RULE_flag = 22, RULE_flagName = 23, RULE_flagValue = 24, RULE_flagValueElement = 25,
		RULE_execForm = 26, RULE_shellForm = 27, RULE_heredoc = 28, RULE_heredocLine = 29,
		RULE_heredocEnd = 30, RULE_jsonArray = 31, RULE_jsonArrayElements = 32,
		RULE_jsonString = 33, RULE_imageName = 34, RULE_stageName = 35, RULE_labelPairs = 36,
		RULE_labelPair = 37, RULE_labelKey = 38, RULE_labelValue = 39, RULE_portList = 40,
		RULE_port = 41, RULE_envPairs = 42, RULE_envPair = 43, RULE_envKey = 44,
		RULE_envValue = 45, RULE_sourceList = 46, RULE_source = 47, RULE_destination = 48,
		RULE_path = 49, RULE_pathList = 50, RULE_userSpec = 51, RULE_argName = 52,
		RULE_argValue = 53, RULE_signal = 54, RULE_text = 55, RULE_textElement = 56,
		RULE_trailingComment = 57;
	private static String[] makeRuleNames() {
		return new String[] {
			"dockerfile", "parserDirective", "instruction", "fromInstruction", "runInstruction",
			"cmdInstruction", "labelInstruction", "exposeInstruction", "envInstruction",
			"addInstruction", "copyInstruction", "entrypointInstruction", "volumeInstruction",
			"userInstruction", "workdirInstruction", "argInstruction", "onbuildInstruction",
			"stopsignalInstruction", "healthcheckInstruction", "shellInstruction",
			"maintainerInstruction", "flags", "flag", "flagName", "flagValue", "flagValueElement",
			"execForm", "shellForm", "heredoc", "heredocLine", "heredocEnd", "jsonArray",
			"jsonArrayElements", "jsonString", "imageName", "stageName", "labelPairs",
			"labelPair", "labelKey", "labelValue", "portList", "port", "envPairs",
			"envPair", "envKey", "envValue", "sourceList", "source", "destination",
			"path", "pathList", "userSpec", "argName", "argValue", "signal", "text",
			"textElement", "trailingComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			"'['", null, "'='", "'--'", null, null, null, null, null, null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PARSER_DIRECTIVE", "COMMENT", "FROM", "RUN", "CMD", "LABEL", "EXPOSE",
			"ENV", "ADD", "COPY", "ENTRYPOINT", "VOLUME", "USER", "WORKDIR", "ARG",
			"ONBUILD", "STOPSIGNAL", "HEALTHCHECK", "SHELL", "MAINTAINER", "AS",
			"HEREDOC_START", "LINE_CONTINUATION", "LBRACKET", "RBRACKET", "EQUALS",
			"DASH_DASH", "DOUBLE_QUOTED_STRING", "SINGLE_QUOTED_STRING", "ENV_VAR",
			"UNQUOTED_TEXT", "WS", "NEWLINE", "JSON_COMMA", "JSON_RBRACKET", "JSON_STRING",
			"JSON_WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DockerfileParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DockerfileParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DockerfileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(DockerfileParser.EOF, 0); }
		public List<ParserDirectiveContext> parserDirective() {
			return getRuleContexts(ParserDirectiveContext.class);
		}
		public ParserDirectiveContext parserDirective(int i) {
			return getRuleContext(ParserDirectiveContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DockerfileParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DockerfileParser.NEWLINE, i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(DockerfileParser.COMMENT); }
		public TerminalNode COMMENT(int i) {
			return getToken(DockerfileParser.COMMENT, i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> LINE_CONTINUATION() { return getTokens(DockerfileParser.LINE_CONTINUATION); }
		public TerminalNode LINE_CONTINUATION(int i) {
			return getToken(DockerfileParser.LINE_CONTINUATION, i);
		}
		public DockerfileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dockerfile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterDockerfile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitDockerfile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitDockerfile(this);
			return visitor.visitChildren(this);
		}
	}

	public final DockerfileContext dockerfile() throws RecognitionException {
		DockerfileContext _localctx = new DockerfileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_dockerfile);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8589934598L) != 0)) {
				{
				setState(119);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PARSER_DIRECTIVE:
					{
					setState(116);
					parserDirective();
					}
					break;
				case NEWLINE:
					{
					setState(117);
					match(NEWLINE);
					}
					break;
				case COMMENT:
					{
					setState(118);
					match(COMMENT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097144L) != 0)) {
				{
				{
				setState(124);
				instruction();
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8598323204L) != 0)) {
					{
					setState(132);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case NEWLINE:
						{
						setState(126);
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(125);
								match(NEWLINE);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(128);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case LINE_CONTINUATION:
						{
						setState(130);
						match(LINE_CONTINUATION);
						}
						break;
					case COMMENT:
						{
						setState(131);
						match(COMMENT);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(142);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParserDirectiveContext extends ParserRuleContext {
		public TerminalNode PARSER_DIRECTIVE() { return getToken(DockerfileParser.PARSER_DIRECTIVE, 0); }
		public ParserDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parserDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterParserDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitParserDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitParserDirective(this);
			return visitor.visitChildren(this);
		}
	}

	public final ParserDirectiveContext parserDirective() throws RecognitionException {
		ParserDirectiveContext _localctx = new ParserDirectiveContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parserDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(PARSER_DIRECTIVE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionContext extends ParserRuleContext {
		public FromInstructionContext fromInstruction() {
			return getRuleContext(FromInstructionContext.class,0);
		}
		public RunInstructionContext runInstruction() {
			return getRuleContext(RunInstructionContext.class,0);
		}
		public CmdInstructionContext cmdInstruction() {
			return getRuleContext(CmdInstructionContext.class,0);
		}
		public LabelInstructionContext labelInstruction() {
			return getRuleContext(LabelInstructionContext.class,0);
		}
		public ExposeInstructionContext exposeInstruction() {
			return getRuleContext(ExposeInstructionContext.class,0);
		}
		public EnvInstructionContext envInstruction() {
			return getRuleContext(EnvInstructionContext.class,0);
		}
		public AddInstructionContext addInstruction() {
			return getRuleContext(AddInstructionContext.class,0);
		}
		public CopyInstructionContext copyInstruction() {
			return getRuleContext(CopyInstructionContext.class,0);
		}
		public EntrypointInstructionContext entrypointInstruction() {
			return getRuleContext(EntrypointInstructionContext.class,0);
		}
		public VolumeInstructionContext volumeInstruction() {
			return getRuleContext(VolumeInstructionContext.class,0);
		}
		public UserInstructionContext userInstruction() {
			return getRuleContext(UserInstructionContext.class,0);
		}
		public WorkdirInstructionContext workdirInstruction() {
			return getRuleContext(WorkdirInstructionContext.class,0);
		}
		public ArgInstructionContext argInstruction() {
			return getRuleContext(ArgInstructionContext.class,0);
		}
		public OnbuildInstructionContext onbuildInstruction() {
			return getRuleContext(OnbuildInstructionContext.class,0);
		}
		public StopsignalInstructionContext stopsignalInstruction() {
			return getRuleContext(StopsignalInstructionContext.class,0);
		}
		public HealthcheckInstructionContext healthcheckInstruction() {
			return getRuleContext(HealthcheckInstructionContext.class,0);
		}
		public ShellInstructionContext shellInstruction() {
			return getRuleContext(ShellInstructionContext.class,0);
		}
		public MaintainerInstructionContext maintainerInstruction() {
			return getRuleContext(MaintainerInstructionContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FROM:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				fromInstruction();
				}
				break;
			case RUN:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				runInstruction();
				}
				break;
			case CMD:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				cmdInstruction();
				}
				break;
			case LABEL:
				enterOuterAlt(_localctx, 4);
				{
				setState(149);
				labelInstruction();
				}
				break;
			case EXPOSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				exposeInstruction();
				}
				break;
			case ENV:
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				envInstruction();
				}
				break;
			case ADD:
				enterOuterAlt(_localctx, 7);
				{
				setState(152);
				addInstruction();
				}
				break;
			case COPY:
				enterOuterAlt(_localctx, 8);
				{
				setState(153);
				copyInstruction();
				}
				break;
			case ENTRYPOINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(154);
				entrypointInstruction();
				}
				break;
			case VOLUME:
				enterOuterAlt(_localctx, 10);
				{
				setState(155);
				volumeInstruction();
				}
				break;
			case USER:
				enterOuterAlt(_localctx, 11);
				{
				setState(156);
				userInstruction();
				}
				break;
			case WORKDIR:
				enterOuterAlt(_localctx, 12);
				{
				setState(157);
				workdirInstruction();
				}
				break;
			case ARG:
				enterOuterAlt(_localctx, 13);
				{
				setState(158);
				argInstruction();
				}
				break;
			case ONBUILD:
				enterOuterAlt(_localctx, 14);
				{
				setState(159);
				onbuildInstruction();
				}
				break;
			case STOPSIGNAL:
				enterOuterAlt(_localctx, 15);
				{
				setState(160);
				stopsignalInstruction();
				}
				break;
			case HEALTHCHECK:
				enterOuterAlt(_localctx, 16);
				{
				setState(161);
				healthcheckInstruction();
				}
				break;
			case SHELL:
				enterOuterAlt(_localctx, 17);
				{
				setState(162);
				shellInstruction();
				}
				break;
			case MAINTAINER:
				enterOuterAlt(_localctx, 18);
				{
				setState(163);
				maintainerInstruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FromInstructionContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(DockerfileParser.FROM, 0); }
		public ImageNameContext imageName() {
			return getRuleContext(ImageNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public TerminalNode AS() { return getToken(DockerfileParser.AS, 0); }
		public StageNameContext stageName() {
			return getRuleContext(StageNameContext.class,0);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public FromInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterFromInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitFromInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitFromInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final FromInstructionContext fromInstruction() throws RecognitionException {
		FromInstructionContext _localctx = new FromInstructionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fromInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(FROM);
			setState(168);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(167);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(172);
				flags();
				setState(174);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(173);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(176);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			setState(180);
			imageName();
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(181);
					match(WS);
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS );
				setState(186);
				match(AS);
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187);
					match(WS);
					}
					}
					setState(190);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS );
				setState(192);
				stageName();
				}
				break;
			}
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(195);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RunInstructionContext extends ParserRuleContext {
		public TerminalNode RUN() { return getToken(DockerfileParser.RUN, 0); }
		public ExecFormContext execForm() {
			return getRuleContext(ExecFormContext.class,0);
		}
		public ShellFormContext shellForm() {
			return getRuleContext(ShellFormContext.class,0);
		}
		public HeredocContext heredoc() {
			return getRuleContext(HeredocContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public RunInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_runInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterRunInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitRunInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitRunInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final RunInstructionContext runInstruction() throws RecognitionException {
		RunInstructionContext _localctx = new RunInstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_runInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(RUN);
			setState(200);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(199);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(204);
				flags();
				setState(206);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(205);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(208);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				{
				setState(212);
				execForm();
				}
				break;
			case COMMENT:
			case LINE_CONTINUATION:
			case EQUALS:
			case DASH_DASH:
			case DOUBLE_QUOTED_STRING:
			case SINGLE_QUOTED_STRING:
			case ENV_VAR:
			case UNQUOTED_TEXT:
			case WS:
				{
				setState(213);
				shellForm();
				}
				break;
			case HEREDOC_START:
				{
				setState(214);
				heredoc();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(217);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdInstructionContext extends ParserRuleContext {
		public TerminalNode CMD() { return getToken(DockerfileParser.CMD, 0); }
		public ExecFormContext execForm() {
			return getRuleContext(ExecFormContext.class,0);
		}
		public ShellFormContext shellForm() {
			return getRuleContext(ShellFormContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public CmdInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterCmdInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitCmdInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitCmdInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final CmdInstructionContext cmdInstruction() throws RecognitionException {
		CmdInstructionContext _localctx = new CmdInstructionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(CMD);
			setState(222);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(221);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(224);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				{
				setState(226);
				execForm();
				}
				break;
			case COMMENT:
			case LINE_CONTINUATION:
			case EQUALS:
			case DASH_DASH:
			case DOUBLE_QUOTED_STRING:
			case SINGLE_QUOTED_STRING:
			case ENV_VAR:
			case UNQUOTED_TEXT:
			case WS:
				{
				setState(227);
				shellForm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(230);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelInstructionContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(DockerfileParser.LABEL, 0); }
		public LabelPairsContext labelPairs() {
			return getRuleContext(LabelPairsContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public LabelInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterLabelInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitLabelInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitLabelInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final LabelInstructionContext labelInstruction() throws RecognitionException {
		LabelInstructionContext _localctx = new LabelInstructionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_labelInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(LABEL);
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(234);
				match(WS);
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(239);
			labelPairs();
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(240);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExposeInstructionContext extends ParserRuleContext {
		public TerminalNode EXPOSE() { return getToken(DockerfileParser.EXPOSE, 0); }
		public PortListContext portList() {
			return getRuleContext(PortListContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public ExposeInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exposeInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterExposeInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitExposeInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitExposeInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final ExposeInstructionContext exposeInstruction() throws RecognitionException {
		ExposeInstructionContext _localctx = new ExposeInstructionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_exposeInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(EXPOSE);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(244);
				match(WS);
				}
				}
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(249);
			portList();
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(250);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnvInstructionContext extends ParserRuleContext {
		public TerminalNode ENV() { return getToken(DockerfileParser.ENV, 0); }
		public EnvPairsContext envPairs() {
			return getRuleContext(EnvPairsContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public EnvInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_envInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterEnvInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitEnvInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitEnvInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final EnvInstructionContext envInstruction() throws RecognitionException {
		EnvInstructionContext _localctx = new EnvInstructionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_envInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(ENV);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(254);
				match(WS);
				}
				}
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(259);
			envPairs();
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(260);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddInstructionContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(DockerfileParser.ADD, 0); }
		public HeredocContext heredoc() {
			return getRuleContext(HeredocContext.class,0);
		}
		public SourceListContext sourceList() {
			return getRuleContext(SourceListContext.class,0);
		}
		public DestinationContext destination() {
			return getRuleContext(DestinationContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public AddInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterAddInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitAddInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitAddInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final AddInstructionContext addInstruction() throws RecognitionException {
		AddInstructionContext _localctx = new AddInstructionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_addInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(ADD);
			setState(265);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(264);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(269);
				flags();
				setState(271);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(270);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(273);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			setState(286);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HEREDOC_START:
				{
				setState(277);
				heredoc();
				}
				break;
			case COMMENT:
			case LINE_CONTINUATION:
			case EQUALS:
			case DASH_DASH:
			case DOUBLE_QUOTED_STRING:
			case SINGLE_QUOTED_STRING:
			case ENV_VAR:
			case UNQUOTED_TEXT:
			case WS:
				{
				setState(278);
				sourceList();
				setState(280);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(279);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(282);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(284);
				destination();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(288);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CopyInstructionContext extends ParserRuleContext {
		public TerminalNode COPY() { return getToken(DockerfileParser.COPY, 0); }
		public HeredocContext heredoc() {
			return getRuleContext(HeredocContext.class,0);
		}
		public SourceListContext sourceList() {
			return getRuleContext(SourceListContext.class,0);
		}
		public DestinationContext destination() {
			return getRuleContext(DestinationContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public CopyInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_copyInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterCopyInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitCopyInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitCopyInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final CopyInstructionContext copyInstruction() throws RecognitionException {
		CopyInstructionContext _localctx = new CopyInstructionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_copyInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(COPY);
			setState(293);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(292);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(295);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(297);
				flags();
				setState(299);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(298);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(301);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HEREDOC_START:
				{
				setState(305);
				heredoc();
				}
				break;
			case COMMENT:
			case LINE_CONTINUATION:
			case EQUALS:
			case DASH_DASH:
			case DOUBLE_QUOTED_STRING:
			case SINGLE_QUOTED_STRING:
			case ENV_VAR:
			case UNQUOTED_TEXT:
			case WS:
				{
				setState(306);
				sourceList();
				setState(308);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(307);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(310);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(312);
				destination();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(316);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EntrypointInstructionContext extends ParserRuleContext {
		public TerminalNode ENTRYPOINT() { return getToken(DockerfileParser.ENTRYPOINT, 0); }
		public ExecFormContext execForm() {
			return getRuleContext(ExecFormContext.class,0);
		}
		public ShellFormContext shellForm() {
			return getRuleContext(ShellFormContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public EntrypointInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entrypointInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterEntrypointInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitEntrypointInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitEntrypointInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final EntrypointInstructionContext entrypointInstruction() throws RecognitionException {
		EntrypointInstructionContext _localctx = new EntrypointInstructionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_entrypointInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(ENTRYPOINT);
			setState(321);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(320);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				{
				setState(325);
				execForm();
				}
				break;
			case COMMENT:
			case LINE_CONTINUATION:
			case EQUALS:
			case DASH_DASH:
			case DOUBLE_QUOTED_STRING:
			case SINGLE_QUOTED_STRING:
			case ENV_VAR:
			case UNQUOTED_TEXT:
			case WS:
				{
				setState(326);
				shellForm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(329);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VolumeInstructionContext extends ParserRuleContext {
		public TerminalNode VOLUME() { return getToken(DockerfileParser.VOLUME, 0); }
		public JsonArrayContext jsonArray() {
			return getRuleContext(JsonArrayContext.class,0);
		}
		public PathListContext pathList() {
			return getRuleContext(PathListContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public VolumeInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_volumeInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterVolumeInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitVolumeInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitVolumeInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final VolumeInstructionContext volumeInstruction() throws RecognitionException {
		VolumeInstructionContext _localctx = new VolumeInstructionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_volumeInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(VOLUME);
			setState(334);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(333);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(336);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(340);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				{
				setState(338);
				jsonArray();
				}
				break;
			case COMMENT:
			case LINE_CONTINUATION:
			case EQUALS:
			case DASH_DASH:
			case DOUBLE_QUOTED_STRING:
			case SINGLE_QUOTED_STRING:
			case ENV_VAR:
			case UNQUOTED_TEXT:
			case WS:
				{
				setState(339);
				pathList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(342);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UserInstructionContext extends ParserRuleContext {
		public TerminalNode USER() { return getToken(DockerfileParser.USER, 0); }
		public UserSpecContext userSpec() {
			return getRuleContext(UserSpecContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public UserInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterUserInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitUserInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitUserInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final UserInstructionContext userInstruction() throws RecognitionException {
		UserInstructionContext _localctx = new UserInstructionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_userInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(USER);
			setState(347);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(346);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(349);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(351);
			userSpec();
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(352);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WorkdirInstructionContext extends ParserRuleContext {
		public TerminalNode WORKDIR() { return getToken(DockerfileParser.WORKDIR, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public WorkdirInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_workdirInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterWorkdirInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitWorkdirInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitWorkdirInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final WorkdirInstructionContext workdirInstruction() throws RecognitionException {
		WorkdirInstructionContext _localctx = new WorkdirInstructionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_workdirInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(WORKDIR);
			setState(357);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(356);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(359);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(361);
			path();
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(362);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgInstructionContext extends ParserRuleContext {
		public TerminalNode ARG() { return getToken(DockerfileParser.ARG, 0); }
		public ArgNameContext argName() {
			return getRuleContext(ArgNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TerminalNode EQUALS() { return getToken(DockerfileParser.EQUALS, 0); }
		public ArgValueContext argValue() {
			return getRuleContext(ArgValueContext.class,0);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public ArgInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterArgInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitArgInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitArgInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final ArgInstructionContext argInstruction() throws RecognitionException {
		ArgInstructionContext _localctx = new ArgInstructionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_argInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(ARG);
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(366);
				match(WS);
				}
				}
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(371);
			argName();
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(372);
				match(EQUALS);
				setState(373);
				argValue();
				}
			}

			setState(377);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(376);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OnbuildInstructionContext extends ParserRuleContext {
		public TerminalNode ONBUILD() { return getToken(DockerfileParser.ONBUILD, 0); }
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public OnbuildInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onbuildInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterOnbuildInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitOnbuildInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitOnbuildInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final OnbuildInstructionContext onbuildInstruction() throws RecognitionException {
		OnbuildInstructionContext _localctx = new OnbuildInstructionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_onbuildInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(ONBUILD);
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(380);
				match(WS);
				}
				}
				setState(383);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(385);
			instruction();
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(386);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StopsignalInstructionContext extends ParserRuleContext {
		public TerminalNode STOPSIGNAL() { return getToken(DockerfileParser.STOPSIGNAL, 0); }
		public SignalContext signal() {
			return getRuleContext(SignalContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public StopsignalInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stopsignalInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterStopsignalInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitStopsignalInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitStopsignalInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final StopsignalInstructionContext stopsignalInstruction() throws RecognitionException {
		StopsignalInstructionContext _localctx = new StopsignalInstructionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stopsignalInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			match(STOPSIGNAL);
			setState(391);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(390);
				match(WS);
				}
				}
				setState(393);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(395);
			signal();
			setState(397);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(396);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HealthcheckInstructionContext extends ParserRuleContext {
		public TerminalNode HEALTHCHECK() { return getToken(DockerfileParser.HEALTHCHECK, 0); }
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public CmdInstructionContext cmdInstruction() {
			return getRuleContext(CmdInstructionContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public HealthcheckInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_healthcheckInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterHealthcheckInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitHealthcheckInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitHealthcheckInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final HealthcheckInstructionContext healthcheckInstruction() throws RecognitionException {
		HealthcheckInstructionContext _localctx = new HealthcheckInstructionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_healthcheckInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			match(HEALTHCHECK);
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(400);
				match(WS);
				}
				}
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(415);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNQUOTED_TEXT:
				{
				setState(405);
				match(UNQUOTED_TEXT);
				}
				break;
			case CMD:
			case DASH_DASH:
				{
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DASH_DASH) {
					{
					setState(406);
					flags();
					setState(408);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(407);
						match(WS);
						}
						}
						setState(410);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WS );
					}
				}

				setState(414);
				cmdInstruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(418);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(417);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShellInstructionContext extends ParserRuleContext {
		public TerminalNode SHELL() { return getToken(DockerfileParser.SHELL, 0); }
		public JsonArrayContext jsonArray() {
			return getRuleContext(JsonArrayContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public ShellInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shellInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterShellInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitShellInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitShellInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final ShellInstructionContext shellInstruction() throws RecognitionException {
		ShellInstructionContext _localctx = new ShellInstructionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_shellInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(SHELL);
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(421);
				match(WS);
				}
				}
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(426);
			jsonArray();
			setState(428);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				{
				setState(427);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MaintainerInstructionContext extends ParserRuleContext {
		public TerminalNode MAINTAINER() { return getToken(DockerfileParser.MAINTAINER, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext trailingComment() {
			return getRuleContext(TrailingCommentContext.class,0);
		}
		public MaintainerInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maintainerInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterMaintainerInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitMaintainerInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitMaintainerInstruction(this);
			return visitor.visitChildren(this);
		}
	}

	public final MaintainerInstructionContext maintainerInstruction() throws RecognitionException {
		MaintainerInstructionContext _localctx = new MaintainerInstructionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_maintainerInstruction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(MAINTAINER);
			setState(432);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(431);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(434);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(436);
			text();
			setState(438);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(437);
				trailingComment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FlagsContext extends ParserRuleContext {
		public List<FlagContext> flag() {
			return getRuleContexts(FlagContext.class);
		}
		public FlagContext flag(int i) {
			return getRuleContext(FlagContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public FlagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterFlags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitFlags(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitFlags(this);
			return visitor.visitChildren(this);
		}
	}

	public final FlagsContext flags() throws RecognitionException {
		FlagsContext _localctx = new FlagsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_flags);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			flag();
			setState(449);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(442);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(441);
						match(WS);
						}
						}
						setState(444);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WS );
					setState(446);
					flag();
					}
					}
				}
				setState(451);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FlagContext extends ParserRuleContext {
		public TerminalNode DASH_DASH() { return getToken(DockerfileParser.DASH_DASH, 0); }
		public FlagNameContext flagName() {
			return getRuleContext(FlagNameContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DockerfileParser.EQUALS, 0); }
		public FlagValueContext flagValue() {
			return getRuleContext(FlagValueContext.class,0);
		}
		public FlagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterFlag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitFlag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitFlag(this);
			return visitor.visitChildren(this);
		}
	}

	public final FlagContext flag() throws RecognitionException {
		FlagContext _localctx = new FlagContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			match(DASH_DASH);
			setState(453);
			flagName();
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(454);
				match(EQUALS);
				setState(455);
				flagValue();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FlagNameContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public TerminalNode FROM() { return getToken(DockerfileParser.FROM, 0); }
		public TerminalNode AS() { return getToken(DockerfileParser.AS, 0); }
		public FlagNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flagName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterFlagName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitFlagName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitFlagName(this);
			return visitor.visitChildren(this);
		}
	}

	public final FlagNameContext flagName() throws RecognitionException {
		FlagNameContext _localctx = new FlagNameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_flagName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2149580808L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FlagValueContext extends ParserRuleContext {
		public List<FlagValueElementContext> flagValueElement() {
			return getRuleContexts(FlagValueElementContext.class);
		}
		public FlagValueElementContext flagValueElement(int i) {
			return getRuleContext(FlagValueElementContext.class,i);
		}
		public FlagValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flagValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterFlagValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitFlagValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitFlagValue(this);
			return visitor.visitChildren(this);
		}
	}

	public final FlagValueContext flagValue() throws RecognitionException {
		FlagValueContext _localctx = new FlagValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_flagValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(460);
				flagValueElement();
				}
				}
				setState(463);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3019898880L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FlagValueElementContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public TerminalNode EQUALS() { return getToken(DockerfileParser.EQUALS, 0); }
		public TerminalNode DOUBLE_QUOTED_STRING() { return getToken(DockerfileParser.DOUBLE_QUOTED_STRING, 0); }
		public TerminalNode SINGLE_QUOTED_STRING() { return getToken(DockerfileParser.SINGLE_QUOTED_STRING, 0); }
		public FlagValueElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flagValueElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterFlagValueElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitFlagValueElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitFlagValueElement(this);
			return visitor.visitChildren(this);
		}
	}

	public final FlagValueElementContext flagValueElement() throws RecognitionException {
		FlagValueElementContext _localctx = new FlagValueElementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_flagValueElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3019898880L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExecFormContext extends ParserRuleContext {
		public JsonArrayContext jsonArray() {
			return getRuleContext(JsonArrayContext.class,0);
		}
		public ExecFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execForm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterExecForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitExecForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitExecForm(this);
			return visitor.visitChildren(this);
		}
	}

	public final ExecFormContext execForm() throws RecognitionException {
		ExecFormContext _localctx = new ExecFormContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_execForm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			jsonArray();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShellFormContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ShellFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shellForm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterShellForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitShellForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitShellForm(this);
			return visitor.visitChildren(this);
		}
	}

	public final ShellFormContext shellForm() throws RecognitionException {
		ShellFormContext _localctx = new ShellFormContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_shellForm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeredocContext extends ParserRuleContext {
		public TerminalNode HEREDOC_START() { return getToken(DockerfileParser.HEREDOC_START, 0); }
		public TerminalNode NEWLINE() { return getToken(DockerfileParser.NEWLINE, 0); }
		public HeredocEndContext heredocEnd() {
			return getRuleContext(HeredocEndContext.class,0);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public List<HeredocLineContext> heredocLine() {
			return getRuleContexts(HeredocLineContext.class);
		}
		public HeredocLineContext heredocLine(int i) {
			return getRuleContext(HeredocLineContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public HeredocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heredoc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterHeredoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitHeredoc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitHeredoc(this);
			return visitor.visitChildren(this);
		}
	}

	public final HeredocContext heredoc() throws RecognitionException {
		HeredocContext _localctx = new HeredocContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_heredoc);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			match(HEREDOC_START);
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(473);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(472);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(475);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(477);
				path();
				}
			}

			setState(480);
			match(NEWLINE);
			setState(484);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(481);
					heredocLine();
					}
					}
				}
				setState(486);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
			}
			setState(487);
			heredocEnd();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeredocLineContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(DockerfileParser.NEWLINE, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public HeredocLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heredocLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterHeredocLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitHeredocLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitHeredocLine(this);
			return visitor.visitChildren(this);
		}
	}

	public final HeredocLineContext heredocLine() throws RecognitionException {
		HeredocLineContext _localctx = new HeredocLineContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_heredocLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8531214340L) != 0)) {
				{
				setState(489);
				text();
				}
			}

			setState(492);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeredocEndContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public HeredocEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heredocEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterHeredocEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitHeredocEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitHeredocEnd(this);
			return visitor.visitChildren(this);
		}
	}

	public final HeredocEndContext heredocEnd() throws RecognitionException {
		HeredocEndContext _localctx = new HeredocEndContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_heredocEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(UNQUOTED_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonArrayContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(DockerfileParser.LBRACKET, 0); }
		public TerminalNode JSON_RBRACKET() { return getToken(DockerfileParser.JSON_RBRACKET, 0); }
		public List<TerminalNode> JSON_WS() { return getTokens(DockerfileParser.JSON_WS); }
		public TerminalNode JSON_WS(int i) {
			return getToken(DockerfileParser.JSON_WS, i);
		}
		public JsonArrayElementsContext jsonArrayElements() {
			return getRuleContext(JsonArrayElementsContext.class,0);
		}
		public JsonArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterJsonArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitJsonArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitJsonArray(this);
			return visitor.visitChildren(this);
		}
	}

	public final JsonArrayContext jsonArray() throws RecognitionException {
		JsonArrayContext _localctx = new JsonArrayContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_jsonArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			match(LBRACKET);
			setState(498);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(497);
				match(JSON_WS);
				}
				break;
			}
			setState(501);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==JSON_STRING) {
				{
				setState(500);
				jsonArrayElements();
				}
			}

			setState(504);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==JSON_WS) {
				{
				setState(503);
				match(JSON_WS);
				}
			}

			setState(506);
			match(JSON_RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonArrayElementsContext extends ParserRuleContext {
		public List<JsonStringContext> jsonString() {
			return getRuleContexts(JsonStringContext.class);
		}
		public JsonStringContext jsonString(int i) {
			return getRuleContext(JsonStringContext.class,i);
		}
		public List<TerminalNode> JSON_COMMA() { return getTokens(DockerfileParser.JSON_COMMA); }
		public TerminalNode JSON_COMMA(int i) {
			return getToken(DockerfileParser.JSON_COMMA, i);
		}
		public List<TerminalNode> JSON_WS() { return getTokens(DockerfileParser.JSON_WS); }
		public TerminalNode JSON_WS(int i) {
			return getToken(DockerfileParser.JSON_WS, i);
		}
		public JsonArrayElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArrayElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterJsonArrayElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitJsonArrayElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitJsonArrayElements(this);
			return visitor.visitChildren(this);
		}
	}

	public final JsonArrayElementsContext jsonArrayElements() throws RecognitionException {
		JsonArrayElementsContext _localctx = new JsonArrayElementsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_jsonArrayElements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(508);
			jsonString();
			setState(519);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(510);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==JSON_WS) {
						{
						setState(509);
						match(JSON_WS);
						}
					}

					setState(512);
					match(JSON_COMMA);
					setState(514);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==JSON_WS) {
						{
						setState(513);
						match(JSON_WS);
						}
					}

					setState(516);
					jsonString();
					}
					}
				}
				setState(521);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonStringContext extends ParserRuleContext {
		public TerminalNode JSON_STRING() { return getToken(DockerfileParser.JSON_STRING, 0); }
		public JsonStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterJsonString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitJsonString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitJsonString(this);
			return visitor.visitChildren(this);
		}
	}

	public final JsonStringContext jsonString() throws RecognitionException {
		JsonStringContext _localctx = new JsonStringContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_jsonString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			match(JSON_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImageNameContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ImageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterImageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitImageName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitImageName(this);
			return visitor.visitChildren(this);
		}
	}

	public final ImageNameContext imageName() throws RecognitionException {
		ImageNameContext _localctx = new ImageNameContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_imageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StageNameContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public StageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterStageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitStageName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitStageName(this);
			return visitor.visitChildren(this);
		}
	}

	public final StageNameContext stageName() throws RecognitionException {
		StageNameContext _localctx = new StageNameContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_stageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(526);
			match(UNQUOTED_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelPairsContext extends ParserRuleContext {
		public List<LabelPairContext> labelPair() {
			return getRuleContexts(LabelPairContext.class);
		}
		public LabelPairContext labelPair(int i) {
			return getRuleContext(LabelPairContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public LabelPairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelPairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterLabelPairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitLabelPairs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitLabelPairs(this);
			return visitor.visitChildren(this);
		}
	}

	public final LabelPairsContext labelPairs() throws RecognitionException {
		LabelPairsContext _localctx = new LabelPairsContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_labelPairs);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			labelPair();
			setState(537);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(530);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(529);
						match(WS);
						}
						}
						setState(532);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WS );
					setState(534);
					labelPair();
					}
					}
				}
				setState(539);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelPairContext extends ParserRuleContext {
		public LabelKeyContext labelKey() {
			return getRuleContext(LabelKeyContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DockerfileParser.EQUALS, 0); }
		public LabelValueContext labelValue() {
			return getRuleContext(LabelValueContext.class,0);
		}
		public LabelPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterLabelPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitLabelPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitLabelPair(this);
			return visitor.visitChildren(this);
		}
	}

	public final LabelPairContext labelPair() throws RecognitionException {
		LabelPairContext _localctx = new LabelPairContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_labelPair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
			labelKey();
			setState(541);
			match(EQUALS);
			setState(542);
			labelValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelKeyContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public TerminalNode DOUBLE_QUOTED_STRING() { return getToken(DockerfileParser.DOUBLE_QUOTED_STRING, 0); }
		public TerminalNode SINGLE_QUOTED_STRING() { return getToken(DockerfileParser.SINGLE_QUOTED_STRING, 0); }
		public LabelKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterLabelKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitLabelKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitLabelKey(this);
			return visitor.visitChildren(this);
		}
	}

	public final LabelKeyContext labelKey() throws RecognitionException {
		LabelKeyContext _localctx = new LabelKeyContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_labelKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2952790016L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelValueContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public TerminalNode DOUBLE_QUOTED_STRING() { return getToken(DockerfileParser.DOUBLE_QUOTED_STRING, 0); }
		public TerminalNode SINGLE_QUOTED_STRING() { return getToken(DockerfileParser.SINGLE_QUOTED_STRING, 0); }
		public LabelValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterLabelValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitLabelValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitLabelValue(this);
			return visitor.visitChildren(this);
		}
	}

	public final LabelValueContext labelValue() throws RecognitionException {
		LabelValueContext _localctx = new LabelValueContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_labelValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2952790016L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PortListContext extends ParserRuleContext {
		public List<PortContext> port() {
			return getRuleContexts(PortContext.class);
		}
		public PortContext port(int i) {
			return getRuleContext(PortContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public PortListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterPortList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitPortList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitPortList(this);
			return visitor.visitChildren(this);
		}
	}

	public final PortListContext portList() throws RecognitionException {
		PortListContext _localctx = new PortListContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_portList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			port();
			setState(557);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(550);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(549);
						match(WS);
						}
						}
						setState(552);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WS );
					setState(554);
					port();
					}
					}
				}
				setState(559);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PortContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public PortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterPort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitPort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitPort(this);
			return visitor.visitChildren(this);
		}
	}

	public final PortContext port() throws RecognitionException {
		PortContext _localctx = new PortContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_port);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			match(UNQUOTED_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnvPairsContext extends ParserRuleContext {
		public List<EnvPairContext> envPair() {
			return getRuleContexts(EnvPairContext.class);
		}
		public EnvPairContext envPair(int i) {
			return getRuleContext(EnvPairContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public EnvPairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_envPairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterEnvPairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitEnvPairs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitEnvPairs(this);
			return visitor.visitChildren(this);
		}
	}

	public final EnvPairsContext envPairs() throws RecognitionException {
		EnvPairsContext _localctx = new EnvPairsContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_envPairs);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
			envPair();
			setState(571);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(564);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(563);
						match(WS);
						}
						}
						setState(566);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WS );
					setState(568);
					envPair();
					}
					}
				}
				setState(573);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnvPairContext extends ParserRuleContext {
		public EnvKeyContext envKey() {
			return getRuleContext(EnvKeyContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(DockerfileParser.EQUALS, 0); }
		public EnvValueContext envValue() {
			return getRuleContext(EnvValueContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public EnvPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_envPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterEnvPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitEnvPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitEnvPair(this);
			return visitor.visitChildren(this);
		}
	}

	public final EnvPairContext envPair() throws RecognitionException {
		EnvPairContext _localctx = new EnvPairContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_envPair);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			envKey();
			setState(583);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(575);
				match(EQUALS);
				setState(576);
				envValue();
				}
				break;
			case WS:
				{
				setState(578);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(577);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(580);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(582);
				envValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnvKeyContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public EnvKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_envKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterEnvKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitEnvKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitEnvKey(this);
			return visitor.visitChildren(this);
		}
	}

	public final EnvKeyContext envKey() throws RecognitionException {
		EnvKeyContext _localctx = new EnvKeyContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_envKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585);
			match(UNQUOTED_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnvValueContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public EnvValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_envValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterEnvValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitEnvValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitEnvValue(this);
			return visitor.visitChildren(this);
		}
	}

	public final EnvValueContext envValue() throws RecognitionException {
		EnvValueContext _localctx = new EnvValueContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_envValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceListContext extends ParserRuleContext {
		public List<SourceContext> source() {
			return getRuleContexts(SourceContext.class);
		}
		public SourceContext source(int i) {
			return getRuleContext(SourceContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public SourceListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterSourceList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitSourceList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitSourceList(this);
			return visitor.visitChildren(this);
		}
	}

	public final SourceListContext sourceList() throws RecognitionException {
		SourceListContext _localctx = new SourceListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_sourceList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
			source();
			setState(598);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(591);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(590);
							match(WS);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(593);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(595);
					source();
					}
					}
				}
				setState(600);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitSource(this);
			return visitor.visitChildren(this);
		}
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(601);
			path();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DestinationContext extends ParserRuleContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public DestinationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destination; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterDestination(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitDestination(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitDestination(this);
			return visitor.visitChildren(this);
		}
	}

	public final DestinationContext destination() throws RecognitionException {
		DestinationContext _localctx = new DestinationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_destination);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			path();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PathContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitPath(this);
			return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_path);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PathListContext extends ParserRuleContext {
		public List<PathContext> path() {
			return getRuleContexts(PathContext.class);
		}
		public PathContext path(int i) {
			return getRuleContext(PathContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public PathListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterPathList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitPathList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitPathList(this);
			return visitor.visitChildren(this);
		}
	}

	public final PathListContext pathList() throws RecognitionException {
		PathListContext _localctx = new PathListContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_pathList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(607);
			path();
			setState(616);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(609);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(608);
							match(WS);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(611);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(613);
					path();
					}
					}
				}
				setState(618);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UserSpecContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public UserSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterUserSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitUserSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitUserSpec(this);
			return visitor.visitChildren(this);
		}
	}

	public final UserSpecContext userSpec() throws RecognitionException {
		UserSpecContext _localctx = new UserSpecContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_userSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgNameContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public ArgNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterArgName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitArgName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitArgName(this);
			return visitor.visitChildren(this);
		}
	}

	public final ArgNameContext argName() throws RecognitionException {
		ArgNameContext _localctx = new ArgNameContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_argName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			match(UNQUOTED_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgValueContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ArgValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterArgValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitArgValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitArgValue(this);
			return visitor.visitChildren(this);
		}
	}

	public final ArgValueContext argValue() throws RecognitionException {
		ArgValueContext _localctx = new ArgValueContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_argValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SignalContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public SignalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterSignal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitSignal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitSignal(this);
			return visitor.visitChildren(this);
		}
	}

	public final SignalContext signal() throws RecognitionException {
		SignalContext _localctx = new SignalContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_signal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(625);
			match(UNQUOTED_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TextContext extends ParserRuleContext {
		public List<TextElementContext> textElement() {
			return getRuleContexts(TextElementContext.class);
		}
		public TextElementContext textElement(int i) {
			return getRuleContext(TextElementContext.class,i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitText(this);
			return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_text);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(627);
					textElement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(630);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TextElementContext extends ParserRuleContext {
		public TerminalNode UNQUOTED_TEXT() { return getToken(DockerfileParser.UNQUOTED_TEXT, 0); }
		public TerminalNode DOUBLE_QUOTED_STRING() { return getToken(DockerfileParser.DOUBLE_QUOTED_STRING, 0); }
		public TerminalNode SINGLE_QUOTED_STRING() { return getToken(DockerfileParser.SINGLE_QUOTED_STRING, 0); }
		public TerminalNode ENV_VAR() { return getToken(DockerfileParser.ENV_VAR, 0); }
		public TerminalNode EQUALS() { return getToken(DockerfileParser.EQUALS, 0); }
		public TerminalNode DASH_DASH() { return getToken(DockerfileParser.DASH_DASH, 0); }
		public TerminalNode LINE_CONTINUATION() { return getToken(DockerfileParser.LINE_CONTINUATION, 0); }
		public TerminalNode WS() { return getToken(DockerfileParser.WS, 0); }
		public TerminalNode COMMENT() { return getToken(DockerfileParser.COMMENT, 0); }
		public TextElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterTextElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitTextElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitTextElement(this);
			return visitor.visitChildren(this);
		}
	}

	public final TextElementContext textElement() throws RecognitionException {
		TextElementContext _localctx = new TextElementContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_textElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(632);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8531214340L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TrailingCommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(DockerfileParser.COMMENT, 0); }
		public List<TerminalNode> WS() { return getTokens(DockerfileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DockerfileParser.WS, i);
		}
		public TrailingCommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trailingComment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).enterTrailingComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DockerfileParserListener ) ((DockerfileParserListener)listener).exitTrailingComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof DockerfileParserVisitor) return ((DockerfileParserVisitor<? extends T>)visitor).visitTrailingComment(this);
			return visitor.visitChildren(this);
		}
	}

	public final TrailingCommentContext trailingComment() throws RecognitionException {
		TrailingCommentContext _localctx = new TrailingCommentContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_trailingComment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(634);
				match(WS);
				}
				}
				setState(639);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(640);
			match(COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u0283\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000x\b\u0000\n\u0000\f\u0000{\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0004\u0000\u007f\b\u0000\u000b\u0000\f\u0000\u0080\u0001\u0000\u0001"+
		"\u0000\u0005\u0000\u0085\b\u0000\n\u0000\f\u0000\u0088\t\u0000\u0005\u0000"+
		"\u008a\b\u0000\n\u0000\f\u0000\u008d\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002\u00a5\b\u0002\u0001\u0003\u0001\u0003\u0004"+
		"\u0003\u00a9\b\u0003\u000b\u0003\f\u0003\u00aa\u0001\u0003\u0001\u0003"+
		"\u0004\u0003\u00af\b\u0003\u000b\u0003\f\u0003\u00b0\u0003\u0003\u00b3"+
		"\b\u0003\u0001\u0003\u0001\u0003\u0004\u0003\u00b7\b\u0003\u000b\u0003"+
		"\f\u0003\u00b8\u0001\u0003\u0001\u0003\u0004\u0003\u00bd\b\u0003\u000b"+
		"\u0003\f\u0003\u00be\u0001\u0003\u0003\u0003\u00c2\b\u0003\u0001\u0003"+
		"\u0003\u0003\u00c5\b\u0003\u0001\u0004\u0001\u0004\u0004\u0004\u00c9\b"+
		"\u0004\u000b\u0004\f\u0004\u00ca\u0001\u0004\u0001\u0004\u0004\u0004\u00cf"+
		"\b\u0004\u000b\u0004\f\u0004\u00d0\u0003\u0004\u00d3\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u00d8\b\u0004\u0001\u0004\u0003\u0004"+
		"\u00db\b\u0004\u0001\u0005\u0001\u0005\u0004\u0005\u00df\b\u0005\u000b"+
		"\u0005\f\u0005\u00e0\u0001\u0005\u0001\u0005\u0003\u0005\u00e5\b\u0005"+
		"\u0001\u0005\u0003\u0005\u00e8\b\u0005\u0001\u0006\u0001\u0006\u0004\u0006"+
		"\u00ec\b\u0006\u000b\u0006\f\u0006\u00ed\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00f2\b\u0006\u0001\u0007\u0001\u0007\u0004\u0007\u00f6\b\u0007"+
		"\u000b\u0007\f\u0007\u00f7\u0001\u0007\u0001\u0007\u0003\u0007\u00fc\b"+
		"\u0007\u0001\b\u0001\b\u0004\b\u0100\b\b\u000b\b\f\b\u0101\u0001\b\u0001"+
		"\b\u0003\b\u0106\b\b\u0001\t\u0001\t\u0004\t\u010a\b\t\u000b\t\f\t\u010b"+
		"\u0001\t\u0001\t\u0004\t\u0110\b\t\u000b\t\f\t\u0111\u0003\t\u0114\b\t"+
		"\u0001\t\u0001\t\u0001\t\u0004\t\u0119\b\t\u000b\t\f\t\u011a\u0001\t\u0001"+
		"\t\u0003\t\u011f\b\t\u0001\t\u0003\t\u0122\b\t\u0001\n\u0001\n\u0004\n"+
		"\u0126\b\n\u000b\n\f\n\u0127\u0001\n\u0001\n\u0004\n\u012c\b\n\u000b\n"+
		"\f\n\u012d\u0003\n\u0130\b\n\u0001\n\u0001\n\u0001\n\u0004\n\u0135\b\n"+
		"\u000b\n\f\n\u0136\u0001\n\u0001\n\u0003\n\u013b\b\n\u0001\n\u0003\n\u013e"+
		"\b\n\u0001\u000b\u0001\u000b\u0004\u000b\u0142\b\u000b\u000b\u000b\f\u000b"+
		"\u0143\u0001\u000b\u0001\u000b\u0003\u000b\u0148\b\u000b\u0001\u000b\u0003"+
		"\u000b\u014b\b\u000b\u0001\f\u0001\f\u0004\f\u014f\b\f\u000b\f\f\f\u0150"+
		"\u0001\f\u0001\f\u0003\f\u0155\b\f\u0001\f\u0003\f\u0158\b\f\u0001\r\u0001"+
		"\r\u0004\r\u015c\b\r\u000b\r\f\r\u015d\u0001\r\u0001\r\u0003\r\u0162\b"+
		"\r\u0001\u000e\u0001\u000e\u0004\u000e\u0166\b\u000e\u000b\u000e\f\u000e"+
		"\u0167\u0001\u000e\u0001\u000e\u0003\u000e\u016c\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0004\u000f\u0170\b\u000f\u000b\u000f\f\u000f\u0171\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u0177\b\u000f\u0001\u000f\u0003\u000f"+
		"\u017a\b\u000f\u0001\u0010\u0001\u0010\u0004\u0010\u017e\b\u0010\u000b"+
		"\u0010\f\u0010\u017f\u0001\u0010\u0001\u0010\u0003\u0010\u0184\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0004\u0011\u0188\b\u0011\u000b\u0011\f\u0011"+
		"\u0189\u0001\u0011\u0001\u0011\u0003\u0011\u018e\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0004\u0012\u0192\b\u0012\u000b\u0012\f\u0012\u0193\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0004\u0012\u0199\b\u0012\u000b\u0012\f\u0012"+
		"\u019a\u0003\u0012\u019d\b\u0012\u0001\u0012\u0003\u0012\u01a0\b\u0012"+
		"\u0001\u0012\u0003\u0012\u01a3\b\u0012\u0001\u0013\u0001\u0013\u0004\u0013"+
		"\u01a7\b\u0013\u000b\u0013\f\u0013\u01a8\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u01ad\b\u0013\u0001\u0014\u0001\u0014\u0004\u0014\u01b1\b\u0014"+
		"\u000b\u0014\f\u0014\u01b2\u0001\u0014\u0001\u0014\u0003\u0014\u01b7\b"+
		"\u0014\u0001\u0015\u0001\u0015\u0004\u0015\u01bb\b\u0015\u000b\u0015\f"+
		"\u0015\u01bc\u0001\u0015\u0005\u0015\u01c0\b\u0015\n\u0015\f\u0015\u01c3"+
		"\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01c9"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0004\u0018\u01ce\b\u0018"+
		"\u000b\u0018\f\u0018\u01cf\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0004\u001c\u01da\b\u001c"+
		"\u000b\u001c\f\u001c\u01db\u0001\u001c\u0003\u001c\u01df\b\u001c\u0001"+
		"\u001c\u0001\u001c\u0005\u001c\u01e3\b\u001c\n\u001c\f\u001c\u01e6\t\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001d\u0003\u001d\u01eb\b\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0003\u001f"+
		"\u01f3\b\u001f\u0001\u001f\u0003\u001f\u01f6\b\u001f\u0001\u001f\u0003"+
		"\u001f\u01f9\b\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0003 \u01ff"+
		"\b \u0001 \u0001 \u0003 \u0203\b \u0001 \u0005 \u0206\b \n \f \u0209\t"+
		" \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0004$\u0213"+
		"\b$\u000b$\f$\u0214\u0001$\u0005$\u0218\b$\n$\f$\u021b\t$\u0001%\u0001"+
		"%\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0004(\u0227"+
		"\b(\u000b(\f(\u0228\u0001(\u0005(\u022c\b(\n(\f(\u022f\t(\u0001)\u0001"+
		")\u0001*\u0001*\u0004*\u0235\b*\u000b*\f*\u0236\u0001*\u0005*\u023a\b"+
		"*\n*\f*\u023d\t*\u0001+\u0001+\u0001+\u0001+\u0004+\u0243\b+\u000b+\f"+
		"+\u0244\u0001+\u0003+\u0248\b+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001"+
		".\u0004.\u0250\b.\u000b.\f.\u0251\u0001.\u0005.\u0255\b.\n.\f.\u0258\t"+
		".\u0001/\u0001/\u00010\u00010\u00011\u00011\u00012\u00012\u00042\u0262"+
		"\b2\u000b2\f2\u0263\u00012\u00052\u0267\b2\n2\f2\u026a\t2\u00013\u0001"+
		"3\u00014\u00014\u00015\u00015\u00016\u00016\u00017\u00047\u0275\b7\u000b"+
		"7\f7\u0276\u00018\u00018\u00019\u00059\u027c\b9\n9\f9\u027f\t9\u00019"+
		"\u00019\u00019\u0000\u0000:\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"TVXZ\\^`bdfhjlnpr\u0000\u0004\u0003\u0000\u0003\u0003\u0015\u0015\u001f"+
		"\u001f\u0003\u0000\u001a\u001a\u001c\u001d\u001f\u001f\u0002\u0000\u001c"+
		"\u001d\u001f\u001f\u0003\u0000\u0002\u0002\u0017\u0017\u001a \u02b9\u0000"+
		"y\u0001\u0000\u0000\u0000\u0002\u0090\u0001\u0000\u0000\u0000\u0004\u00a4"+
		"\u0001\u0000\u0000\u0000\u0006\u00a6\u0001\u0000\u0000\u0000\b\u00c6\u0001"+
		"\u0000\u0000\u0000\n\u00dc\u0001\u0000\u0000\u0000\f\u00e9\u0001\u0000"+
		"\u0000\u0000\u000e\u00f3\u0001\u0000\u0000\u0000\u0010\u00fd\u0001\u0000"+
		"\u0000\u0000\u0012\u0107\u0001\u0000\u0000\u0000\u0014\u0123\u0001\u0000"+
		"\u0000\u0000\u0016\u013f\u0001\u0000\u0000\u0000\u0018\u014c\u0001\u0000"+
		"\u0000\u0000\u001a\u0159\u0001\u0000\u0000\u0000\u001c\u0163\u0001\u0000"+
		"\u0000\u0000\u001e\u016d\u0001\u0000\u0000\u0000 \u017b\u0001\u0000\u0000"+
		"\u0000\"\u0185\u0001\u0000\u0000\u0000$\u018f\u0001\u0000\u0000\u0000"+
		"&\u01a4\u0001\u0000\u0000\u0000(\u01ae\u0001\u0000\u0000\u0000*\u01b8"+
		"\u0001\u0000\u0000\u0000,\u01c4\u0001\u0000\u0000\u0000.\u01ca\u0001\u0000"+
		"\u0000\u00000\u01cd\u0001\u0000\u0000\u00002\u01d1\u0001\u0000\u0000\u0000"+
		"4\u01d3\u0001\u0000\u0000\u00006\u01d5\u0001\u0000\u0000\u00008\u01d7"+
		"\u0001\u0000\u0000\u0000:\u01ea\u0001\u0000\u0000\u0000<\u01ee\u0001\u0000"+
		"\u0000\u0000>\u01f0\u0001\u0000\u0000\u0000@\u01fc\u0001\u0000\u0000\u0000"+
		"B\u020a\u0001\u0000\u0000\u0000D\u020c\u0001\u0000\u0000\u0000F\u020e"+
		"\u0001\u0000\u0000\u0000H\u0210\u0001\u0000\u0000\u0000J\u021c\u0001\u0000"+
		"\u0000\u0000L\u0220\u0001\u0000\u0000\u0000N\u0222\u0001\u0000\u0000\u0000"+
		"P\u0224\u0001\u0000\u0000\u0000R\u0230\u0001\u0000\u0000\u0000T\u0232"+
		"\u0001\u0000\u0000\u0000V\u023e\u0001\u0000\u0000\u0000X\u0249\u0001\u0000"+
		"\u0000\u0000Z\u024b\u0001\u0000\u0000\u0000\\\u024d\u0001\u0000\u0000"+
		"\u0000^\u0259\u0001\u0000\u0000\u0000`\u025b\u0001\u0000\u0000\u0000b"+
		"\u025d\u0001\u0000\u0000\u0000d\u025f\u0001\u0000\u0000\u0000f\u026b\u0001"+
		"\u0000\u0000\u0000h\u026d\u0001\u0000\u0000\u0000j\u026f\u0001\u0000\u0000"+
		"\u0000l\u0271\u0001\u0000\u0000\u0000n\u0274\u0001\u0000\u0000\u0000p"+
		"\u0278\u0001\u0000\u0000\u0000r\u027d\u0001\u0000\u0000\u0000tx\u0003"+
		"\u0002\u0001\u0000ux\u0005!\u0000\u0000vx\u0005\u0002\u0000\u0000wt\u0001"+
		"\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wv\u0001\u0000\u0000\u0000"+
		"x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000"+
		"\u0000z\u008b\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000|\u0086"+
		"\u0003\u0004\u0002\u0000}\u007f\u0005!\u0000\u0000~}\u0001\u0000\u0000"+
		"\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000"+
		"\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0085\u0001\u0000\u0000\u0000"+
		"\u0082\u0085\u0005\u0017\u0000\u0000\u0083\u0085\u0005\u0002\u0000\u0000"+
		"\u0084~\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084"+
		"\u0083\u0001\u0000\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000\u0086"+
		"\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087"+
		"\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089"+
		"|\u0001\u0000\u0000\u0000\u008a\u008d\u0001\u0000\u0000\u0000\u008b\u0089"+
		"\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008e"+
		"\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005\u0000\u0000\u0001\u008f\u0001\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0005\u0001\u0000\u0000\u0091\u0003\u0001\u0000\u0000\u0000\u0092\u00a5"+
		"\u0003\u0006\u0003\u0000\u0093\u00a5\u0003\b\u0004\u0000\u0094\u00a5\u0003"+
		"\n\u0005\u0000\u0095\u00a5\u0003\f\u0006\u0000\u0096\u00a5\u0003\u000e"+
		"\u0007\u0000\u0097\u00a5\u0003\u0010\b\u0000\u0098\u00a5\u0003\u0012\t"+
		"\u0000\u0099\u00a5\u0003\u0014\n\u0000\u009a\u00a5\u0003\u0016\u000b\u0000"+
		"\u009b\u00a5\u0003\u0018\f\u0000\u009c\u00a5\u0003\u001a\r\u0000\u009d"+
		"\u00a5\u0003\u001c\u000e\u0000\u009e\u00a5\u0003\u001e\u000f\u0000\u009f"+
		"\u00a5\u0003 \u0010\u0000\u00a0\u00a5\u0003\"\u0011\u0000\u00a1\u00a5"+
		"\u0003$\u0012\u0000\u00a2\u00a5\u0003&\u0013\u0000\u00a3\u00a5\u0003("+
		"\u0014\u0000\u00a4\u0092\u0001\u0000\u0000\u0000\u00a4\u0093\u0001\u0000"+
		"\u0000\u0000\u00a4\u0094\u0001\u0000\u0000\u0000\u00a4\u0095\u0001\u0000"+
		"\u0000\u0000\u00a4\u0096\u0001\u0000\u0000\u0000\u00a4\u0097\u0001\u0000"+
		"\u0000\u0000\u00a4\u0098\u0001\u0000\u0000\u0000\u00a4\u0099\u0001\u0000"+
		"\u0000\u0000\u00a4\u009a\u0001\u0000\u0000\u0000\u00a4\u009b\u0001\u0000"+
		"\u0000\u0000\u00a4\u009c\u0001\u0000\u0000\u0000\u00a4\u009d\u0001\u0000"+
		"\u0000\u0000\u00a4\u009e\u0001\u0000\u0000\u0000\u00a4\u009f\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a0\u0001\u0000\u0000\u0000\u00a4\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a5\u0005\u0001\u0000\u0000\u0000\u00a6\u00a8\u0005\u0003"+
		"\u0000\u0000\u00a7\u00a9\u0005 \u0000\u0000\u00a8\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00b2\u0001\u0000\u0000"+
		"\u0000\u00ac\u00ae\u0003*\u0015\u0000\u00ad\u00af\u0005 \u0000\u0000\u00ae"+
		"\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0"+
		"\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b2\u00ac\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4"+
		"\u00c1\u0003D\"\u0000\u00b5\u00b7\u0005 \u0000\u0000\u00b6\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bc\u0005\u0015\u0000\u0000\u00bb\u00bd\u0005"+
		" \u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000"+
		"\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c2\u0003F#\u0000"+
		"\u00c1\u00b6\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3\u00c5\u0003r9\u0000\u00c4\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u0007"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c8\u0005\u0004\u0000\u0000\u00c7\u00c9"+
		"\u0005 \u0000\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001"+
		"\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001"+
		"\u0000\u0000\u0000\u00cb\u00d2\u0001\u0000\u0000\u0000\u00cc\u00ce\u0003"+
		"*\u0015\u0000\u00cd\u00cf\u0005 \u0000\u0000\u00ce\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d2\u00cc\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d7\u0001\u0000\u0000\u0000\u00d4\u00d8\u00034\u001a\u0000"+
		"\u00d5\u00d8\u00036\u001b\u0000\u00d6\u00d8\u00038\u001c\u0000\u00d7\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d8\u00da\u0001\u0000\u0000\u0000\u00d9\u00db"+
		"\u0003r9\u0000\u00da\u00d9\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000"+
		"\u0000\u0000\u00db\t\u0001\u0000\u0000\u0000\u00dc\u00de\u0005\u0005\u0000"+
		"\u0000\u00dd\u00df\u0005 \u0000\u0000\u00de\u00dd\u0001\u0000\u0000\u0000"+
		"\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e5\u00034\u001a\u0000\u00e3\u00e5\u00036\u001b\u0000\u00e4\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e8\u0003r9\u0000\u00e7\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u000b\u0001\u0000"+
		"\u0000\u0000\u00e9\u00eb\u0005\u0006\u0000\u0000\u00ea\u00ec\u0005 \u0000"+
		"\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000"+
		"\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f1\u0003H$\u0000\u00f0"+
		"\u00f2\u0003r9\u0000\u00f1\u00f0\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f2\r\u0001\u0000\u0000\u0000\u00f3\u00f5\u0005\u0007"+
		"\u0000\u0000\u00f4\u00f6\u0005 \u0000\u0000\u00f5\u00f4\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fb\u0003P(\u0000\u00fa\u00fc\u0003r9\u0000\u00fb\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u000f"+
		"\u0001\u0000\u0000\u0000\u00fd\u00ff\u0005\b\u0000\u0000\u00fe\u0100\u0005"+
		" \u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000"+
		"\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000"+
		"\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0105\u0003T*\u0000"+
		"\u0104\u0106\u0003r9\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u0105\u0106"+
		"\u0001\u0000\u0000\u0000\u0106\u0011\u0001\u0000\u0000\u0000\u0107\u0109"+
		"\u0005\t\u0000\u0000\u0108\u010a\u0005 \u0000\u0000\u0109\u0108\u0001"+
		"\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b\u0109\u0001"+
		"\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u0113\u0001"+
		"\u0000\u0000\u0000\u010d\u010f\u0003*\u0015\u0000\u010e\u0110\u0005 \u0000"+
		"\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000"+
		"\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000"+
		"\u0000\u0112\u0114\u0001\u0000\u0000\u0000\u0113\u010d\u0001\u0000\u0000"+
		"\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u011e\u0001\u0000\u0000"+
		"\u0000\u0115\u011f\u00038\u001c\u0000\u0116\u0118\u0003\\.\u0000\u0117"+
		"\u0119\u0005 \u0000\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0119\u011a"+
		"\u0001\u0000\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000\u011a\u011b"+
		"\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011d"+
		"\u0003`0\u0000\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u0115\u0001\u0000"+
		"\u0000\u0000\u011e\u0116\u0001\u0000\u0000\u0000\u011f\u0121\u0001\u0000"+
		"\u0000\u0000\u0120\u0122\u0003r9\u0000\u0121\u0120\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0013\u0001\u0000\u0000\u0000"+
		"\u0123\u0125\u0005\n\u0000\u0000\u0124\u0126\u0005 \u0000\u0000\u0125"+
		"\u0124\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127"+
		"\u0125\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128"+
		"\u012f\u0001\u0000\u0000\u0000\u0129\u012b\u0003*\u0015\u0000\u012a\u012c"+
		"\u0005 \u0000\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001"+
		"\u0000\u0000\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001"+
		"\u0000\u0000\u0000\u012e\u0130\u0001\u0000\u0000\u0000\u012f\u0129\u0001"+
		"\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u013a\u0001"+
		"\u0000\u0000\u0000\u0131\u013b\u00038\u001c\u0000\u0132\u0134\u0003\\"+
		".\u0000\u0133\u0135\u0005 \u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000"+
		"\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000"+
		"\u0136\u0137\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000"+
		"\u0138\u0139\u0003`0\u0000\u0139\u013b\u0001\u0000\u0000\u0000\u013a\u0131"+
		"\u0001\u0000\u0000\u0000\u013a\u0132\u0001\u0000\u0000\u0000\u013b\u013d"+
		"\u0001\u0000\u0000\u0000\u013c\u013e\u0003r9\u0000\u013d\u013c\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0015\u0001\u0000"+
		"\u0000\u0000\u013f\u0141\u0005\u000b\u0000\u0000\u0140\u0142\u0005 \u0000"+
		"\u0000\u0141\u0140\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000\u0000"+
		"\u0000\u0143\u0141\u0001\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000"+
		"\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145\u0148\u00034\u001a\u0000"+
		"\u0146\u0148\u00036\u001b\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0147"+
		"\u0146\u0001\u0000\u0000\u0000\u0148\u014a\u0001\u0000\u0000\u0000\u0149"+
		"\u014b\u0003r9\u0000\u014a\u0149\u0001\u0000\u0000\u0000\u014a\u014b\u0001"+
		"\u0000\u0000\u0000\u014b\u0017\u0001\u0000\u0000\u0000\u014c\u014e\u0005"+
		"\f\u0000\u0000\u014d\u014f\u0005 \u0000\u0000\u014e\u014d\u0001\u0000"+
		"\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u014e\u0001\u0000"+
		"\u0000\u0000\u0150\u0151\u0001\u0000\u0000\u0000\u0151\u0154\u0001\u0000"+
		"\u0000\u0000\u0152\u0155\u0003>\u001f\u0000\u0153\u0155\u0003d2\u0000"+
		"\u0154\u0152\u0001\u0000\u0000\u0000\u0154\u0153\u0001\u0000\u0000\u0000"+
		"\u0155\u0157\u0001\u0000\u0000\u0000\u0156\u0158\u0003r9\u0000\u0157\u0156"+
		"\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158\u0019"+
		"\u0001\u0000\u0000\u0000\u0159\u015b\u0005\r\u0000\u0000\u015a\u015c\u0005"+
		" \u0000\u0000\u015b\u015a\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000"+
		"\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000"+
		"\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0161\u0003f3\u0000"+
		"\u0160\u0162\u0003r9\u0000\u0161\u0160\u0001\u0000\u0000\u0000\u0161\u0162"+
		"\u0001\u0000\u0000\u0000\u0162\u001b\u0001\u0000\u0000\u0000\u0163\u0165"+
		"\u0005\u000e\u0000\u0000\u0164\u0166\u0005 \u0000\u0000\u0165\u0164\u0001"+
		"\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0165\u0001"+
		"\u0000\u0000\u0000\u0167\u0168\u0001\u0000\u0000\u0000\u0168\u0169\u0001"+
		"\u0000\u0000\u0000\u0169\u016b\u0003b1\u0000\u016a\u016c\u0003r9\u0000"+
		"\u016b\u016a\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000"+
		"\u016c\u001d\u0001\u0000\u0000\u0000\u016d\u016f\u0005\u000f\u0000\u0000"+
		"\u016e\u0170\u0005 \u0000\u0000\u016f\u016e\u0001\u0000\u0000\u0000\u0170"+
		"\u0171\u0001\u0000\u0000\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0171"+
		"\u0172\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173"+
		"\u0176\u0003h4\u0000\u0174\u0175\u0005\u001a\u0000\u0000\u0175\u0177\u0003"+
		"j5\u0000\u0176\u0174\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000\u0000"+
		"\u0000\u0177\u0179\u0001\u0000\u0000\u0000\u0178\u017a\u0003r9\u0000\u0179"+
		"\u0178\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a"+
		"\u001f\u0001\u0000\u0000\u0000\u017b\u017d\u0005\u0010\u0000\u0000\u017c"+
		"\u017e\u0005 \u0000\u0000\u017d\u017c\u0001\u0000\u0000\u0000\u017e\u017f"+
		"\u0001\u0000\u0000\u0000\u017f\u017d\u0001\u0000\u0000\u0000\u017f\u0180"+
		"\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0183"+
		"\u0003\u0004\u0002\u0000\u0182\u0184\u0003r9\u0000\u0183\u0182\u0001\u0000"+
		"\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184!\u0001\u0000\u0000"+
		"\u0000\u0185\u0187\u0005\u0011\u0000\u0000\u0186\u0188\u0005 \u0000\u0000"+
		"\u0187\u0186\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000\u0000"+
		"\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000\u0000"+
		"\u018a\u018b\u0001\u0000\u0000\u0000\u018b\u018d\u0003l6\u0000\u018c\u018e"+
		"\u0003r9\u0000\u018d\u018c\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000"+
		"\u0000\u0000\u018e#\u0001\u0000\u0000\u0000\u018f\u0191\u0005\u0012\u0000"+
		"\u0000\u0190\u0192\u0005 \u0000\u0000\u0191\u0190\u0001\u0000\u0000\u0000"+
		"\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000"+
		"\u0193\u0194\u0001\u0000\u0000\u0000\u0194\u019f\u0001\u0000\u0000\u0000"+
		"\u0195\u01a0\u0005\u001f\u0000\u0000\u0196\u0198\u0003*\u0015\u0000\u0197"+
		"\u0199\u0005 \u0000\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0199\u019a"+
		"\u0001\u0000\u0000\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u019b"+
		"\u0001\u0000\u0000\u0000\u019b\u019d\u0001\u0000\u0000\u0000\u019c\u0196"+
		"\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u019e"+
		"\u0001\u0000\u0000\u0000\u019e\u01a0\u0003\n\u0005\u0000\u019f\u0195\u0001"+
		"\u0000\u0000\u0000\u019f\u019c\u0001\u0000\u0000\u0000\u01a0\u01a2\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a3\u0003r9\u0000\u01a2\u01a1\u0001\u0000\u0000"+
		"\u0000\u01a2\u01a3\u0001\u0000\u0000\u0000\u01a3%\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a6\u0005\u0013\u0000\u0000\u01a5\u01a7\u0005 \u0000\u0000\u01a6"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8"+
		"\u01a6\u0001\u0000\u0000\u0000\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a9"+
		"\u01aa\u0001\u0000\u0000\u0000\u01aa\u01ac\u0003>\u001f\u0000\u01ab\u01ad"+
		"\u0003r9\u0000\u01ac\u01ab\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000"+
		"\u0000\u0000\u01ad\'\u0001\u0000\u0000\u0000\u01ae\u01b0\u0005\u0014\u0000"+
		"\u0000\u01af\u01b1\u0005 \u0000\u0000\u01b0\u01af\u0001\u0000\u0000\u0000"+
		"\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000"+
		"\u01b2\u01b3\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b4\u01b6\u0003n7\u0000\u01b5\u01b7\u0003r9\u0000\u01b6\u01b5\u0001"+
		"\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7)\u0001\u0000"+
		"\u0000\u0000\u01b8\u01c1\u0003,\u0016\u0000\u01b9\u01bb\u0005 \u0000\u0000"+
		"\u01ba\u01b9\u0001\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000"+
		"\u01bc\u01ba\u0001\u0000\u0000\u0000\u01bc\u01bd\u0001\u0000\u0000\u0000"+
		"\u01bd\u01be\u0001\u0000\u0000\u0000\u01be\u01c0\u0003,\u0016\u0000\u01bf"+
		"\u01ba\u0001\u0000\u0000\u0000\u01c0\u01c3\u0001\u0000\u0000\u0000\u01c1"+
		"\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2"+
		"+\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c4\u01c5"+
		"\u0005\u001b\u0000\u0000\u01c5\u01c8\u0003.\u0017\u0000\u01c6\u01c7\u0005"+
		"\u001a\u0000\u0000\u01c7\u01c9\u00030\u0018\u0000\u01c8\u01c6\u0001\u0000"+
		"\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9-\u0001\u0000\u0000"+
		"\u0000\u01ca\u01cb\u0007\u0000\u0000\u0000\u01cb/\u0001\u0000\u0000\u0000"+
		"\u01cc\u01ce\u00032\u0019\u0000\u01cd\u01cc\u0001\u0000\u0000\u0000\u01ce"+
		"\u01cf\u0001\u0000\u0000\u0000\u01cf\u01cd\u0001\u0000\u0000\u0000\u01cf"+
		"\u01d0\u0001\u0000\u0000\u0000\u01d01\u0001\u0000\u0000\u0000\u01d1\u01d2"+
		"\u0007\u0001\u0000\u0000\u01d23\u0001\u0000\u0000\u0000\u01d3\u01d4\u0003"+
		">\u001f\u0000\u01d45\u0001\u0000\u0000\u0000\u01d5\u01d6\u0003n7\u0000"+
		"\u01d67\u0001\u0000\u0000\u0000\u01d7\u01de\u0005\u0016\u0000\u0000\u01d8"+
		"\u01da\u0005 \u0000\u0000\u01d9\u01d8\u0001\u0000\u0000\u0000\u01da\u01db"+
		"\u0001\u0000\u0000\u0000\u01db\u01d9\u0001\u0000\u0000\u0000\u01db\u01dc"+
		"\u0001\u0000\u0000\u0000\u01dc\u01dd\u0001\u0000\u0000\u0000\u01dd\u01df"+
		"\u0003b1\u0000\u01de\u01d9\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000"+
		"\u0000\u0000\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0\u01e4\u0005!\u0000"+
		"\u0000\u01e1\u01e3\u0003:\u001d\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e6\u0001\u0000\u0000\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000"+
		"\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e7\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e4\u0001\u0000\u0000\u0000\u01e7\u01e8\u0003<\u001e\u0000\u01e8"+
		"9\u0001\u0000\u0000\u0000\u01e9\u01eb\u0003n7\u0000\u01ea\u01e9\u0001"+
		"\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001"+
		"\u0000\u0000\u0000\u01ec\u01ed\u0005!\u0000\u0000\u01ed;\u0001\u0000\u0000"+
		"\u0000\u01ee\u01ef\u0005\u001f\u0000\u0000\u01ef=\u0001\u0000\u0000\u0000"+
		"\u01f0\u01f2\u0005\u0018\u0000\u0000\u01f1\u01f3\u0005%\u0000\u0000\u01f2"+
		"\u01f1\u0001\u0000\u0000\u0000\u01f2\u01f3\u0001\u0000\u0000\u0000\u01f3"+
		"\u01f5\u0001\u0000\u0000\u0000\u01f4\u01f6\u0003@ \u0000\u01f5\u01f4\u0001"+
		"\u0000\u0000\u0000\u01f5\u01f6\u0001\u0000\u0000\u0000\u01f6\u01f8\u0001"+
		"\u0000\u0000\u0000\u01f7\u01f9\u0005%\u0000\u0000\u01f8\u01f7\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f9\u0001\u0000\u0000\u0000\u01f9\u01fa\u0001\u0000"+
		"\u0000\u0000\u01fa\u01fb\u0005#\u0000\u0000\u01fb?\u0001\u0000\u0000\u0000"+
		"\u01fc\u0207\u0003B!\u0000\u01fd\u01ff\u0005%\u0000\u0000\u01fe\u01fd"+
		"\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff\u0200"+
		"\u0001\u0000\u0000\u0000\u0200\u0202\u0005\"\u0000\u0000\u0201\u0203\u0005"+
		"%\u0000\u0000\u0202\u0201\u0001\u0000\u0000\u0000\u0202\u0203\u0001\u0000"+
		"\u0000\u0000\u0203\u0204\u0001\u0000\u0000\u0000\u0204\u0206\u0003B!\u0000"+
		"\u0205\u01fe\u0001\u0000\u0000\u0000\u0206\u0209\u0001\u0000\u0000\u0000"+
		"\u0207\u0205\u0001\u0000\u0000\u0000\u0207\u0208\u0001\u0000\u0000\u0000"+
		"\u0208A\u0001\u0000\u0000\u0000\u0209\u0207\u0001\u0000\u0000\u0000\u020a"+
		"\u020b\u0005$\u0000\u0000\u020bC\u0001\u0000\u0000\u0000\u020c\u020d\u0003"+
		"n7\u0000\u020dE\u0001\u0000\u0000\u0000\u020e\u020f\u0005\u001f\u0000"+
		"\u0000\u020fG\u0001\u0000\u0000\u0000\u0210\u0219\u0003J%\u0000\u0211"+
		"\u0213\u0005 \u0000\u0000\u0212\u0211\u0001\u0000\u0000\u0000\u0213\u0214"+
		"\u0001\u0000\u0000\u0000\u0214\u0212\u0001\u0000\u0000\u0000\u0214\u0215"+
		"\u0001\u0000\u0000\u0000\u0215\u0216\u0001\u0000\u0000\u0000\u0216\u0218"+
		"\u0003J%\u0000\u0217\u0212\u0001\u0000\u0000\u0000\u0218\u021b\u0001\u0000"+
		"\u0000\u0000\u0219\u0217\u0001\u0000\u0000\u0000\u0219\u021a\u0001\u0000"+
		"\u0000\u0000\u021aI\u0001\u0000\u0000\u0000\u021b\u0219\u0001\u0000\u0000"+
		"\u0000\u021c\u021d\u0003L&\u0000\u021d\u021e\u0005\u001a\u0000\u0000\u021e"+
		"\u021f\u0003N\'\u0000\u021fK\u0001\u0000\u0000\u0000\u0220\u0221\u0007"+
		"\u0002\u0000\u0000\u0221M\u0001\u0000\u0000\u0000\u0222\u0223\u0007\u0002"+
		"\u0000\u0000\u0223O\u0001\u0000\u0000\u0000\u0224\u022d\u0003R)\u0000"+
		"\u0225\u0227\u0005 \u0000\u0000\u0226\u0225\u0001\u0000\u0000\u0000\u0227"+
		"\u0228\u0001\u0000\u0000\u0000\u0228\u0226\u0001\u0000\u0000\u0000\u0228"+
		"\u0229\u0001\u0000\u0000\u0000\u0229\u022a\u0001\u0000\u0000\u0000\u022a"+
		"\u022c\u0003R)\u0000\u022b\u0226\u0001\u0000\u0000\u0000\u022c\u022f\u0001"+
		"\u0000\u0000\u0000\u022d\u022b\u0001\u0000\u0000\u0000\u022d\u022e\u0001"+
		"\u0000\u0000\u0000\u022eQ\u0001\u0000\u0000\u0000\u022f\u022d\u0001\u0000"+
		"\u0000\u0000\u0230\u0231\u0005\u001f\u0000\u0000\u0231S\u0001\u0000\u0000"+
		"\u0000\u0232\u023b\u0003V+\u0000\u0233\u0235\u0005 \u0000\u0000\u0234"+
		"\u0233\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000\u0000\u0000\u0236"+
		"\u0234\u0001\u0000\u0000\u0000\u0236\u0237\u0001\u0000\u0000\u0000\u0237"+
		"\u0238\u0001\u0000\u0000\u0000\u0238\u023a\u0003V+\u0000\u0239\u0234\u0001"+
		"\u0000\u0000\u0000\u023a\u023d\u0001\u0000\u0000\u0000\u023b\u0239\u0001"+
		"\u0000\u0000\u0000\u023b\u023c\u0001\u0000\u0000\u0000\u023cU\u0001\u0000"+
		"\u0000\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023e\u0247\u0003X,\u0000"+
		"\u023f\u0240\u0005\u001a\u0000\u0000\u0240\u0248\u0003Z-\u0000\u0241\u0243"+
		"\u0005 \u0000\u0000\u0242\u0241\u0001\u0000\u0000\u0000\u0243\u0244\u0001"+
		"\u0000\u0000\u0000\u0244\u0242\u0001\u0000\u0000\u0000\u0244\u0245\u0001"+
		"\u0000\u0000\u0000\u0245\u0246\u0001\u0000\u0000\u0000\u0246\u0248\u0003"+
		"Z-\u0000\u0247\u023f\u0001\u0000\u0000\u0000\u0247\u0242\u0001\u0000\u0000"+
		"\u0000\u0248W\u0001\u0000\u0000\u0000\u0249\u024a\u0005\u001f\u0000\u0000"+
		"\u024aY\u0001\u0000\u0000\u0000\u024b\u024c\u0003n7\u0000\u024c[\u0001"+
		"\u0000\u0000\u0000\u024d\u0256\u0003^/\u0000\u024e\u0250\u0005 \u0000"+
		"\u0000\u024f\u024e\u0001\u0000\u0000\u0000\u0250\u0251\u0001\u0000\u0000"+
		"\u0000\u0251\u024f\u0001\u0000\u0000\u0000\u0251\u0252\u0001\u0000\u0000"+
		"\u0000\u0252\u0253\u0001\u0000\u0000\u0000\u0253\u0255\u0003^/\u0000\u0254"+
		"\u024f\u0001\u0000\u0000\u0000\u0255\u0258\u0001\u0000\u0000\u0000\u0256"+
		"\u0254\u0001\u0000\u0000\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u0257"+
		"]\u0001\u0000\u0000\u0000\u0258\u0256\u0001\u0000\u0000\u0000\u0259\u025a"+
		"\u0003b1\u0000\u025a_\u0001\u0000\u0000\u0000\u025b\u025c\u0003b1\u0000"+
		"\u025ca\u0001\u0000\u0000\u0000\u025d\u025e\u0003n7\u0000\u025ec\u0001"+
		"\u0000\u0000\u0000\u025f\u0268\u0003b1\u0000\u0260\u0262\u0005 \u0000"+
		"\u0000\u0261\u0260\u0001\u0000\u0000\u0000\u0262\u0263\u0001\u0000\u0000"+
		"\u0000\u0263\u0261\u0001\u0000\u0000\u0000\u0263\u0264\u0001\u0000\u0000"+
		"\u0000\u0264\u0265\u0001\u0000\u0000\u0000\u0265\u0267\u0003b1\u0000\u0266"+
		"\u0261\u0001\u0000\u0000\u0000\u0267\u026a\u0001\u0000\u0000\u0000\u0268"+
		"\u0266\u0001\u0000\u0000\u0000\u0268\u0269\u0001\u0000\u0000\u0000\u0269"+
		"e\u0001\u0000\u0000\u0000\u026a\u0268\u0001\u0000\u0000\u0000\u026b\u026c"+
		"\u0003n7\u0000\u026cg\u0001\u0000\u0000\u0000\u026d\u026e\u0005\u001f"+
		"\u0000\u0000\u026ei\u0001\u0000\u0000\u0000\u026f\u0270\u0003n7\u0000"+
		"\u0270k\u0001\u0000\u0000\u0000\u0271\u0272\u0005\u001f\u0000\u0000\u0272"+
		"m\u0001\u0000\u0000\u0000\u0273\u0275\u0003p8\u0000\u0274\u0273\u0001"+
		"\u0000\u0000\u0000\u0275\u0276\u0001\u0000\u0000\u0000\u0276\u0274\u0001"+
		"\u0000\u0000\u0000\u0276\u0277\u0001\u0000\u0000\u0000\u0277o\u0001\u0000"+
		"\u0000\u0000\u0278\u0279\u0007\u0003\u0000\u0000\u0279q\u0001\u0000\u0000"+
		"\u0000\u027a\u027c\u0005 \u0000\u0000\u027b\u027a\u0001\u0000\u0000\u0000"+
		"\u027c\u027f\u0001\u0000\u0000\u0000\u027d\u027b\u0001\u0000\u0000\u0000"+
		"\u027d\u027e\u0001\u0000\u0000\u0000\u027e\u0280\u0001\u0000\u0000\u0000"+
		"\u027f\u027d\u0001\u0000\u0000\u0000\u0280\u0281\u0005\u0002\u0000\u0000"+
		"\u0281s\u0001\u0000\u0000\u0000^wy\u0080\u0084\u0086\u008b\u00a4\u00aa"+
		"\u00b0\u00b2\u00b8\u00be\u00c1\u00c4\u00ca\u00d0\u00d2\u00d7\u00da\u00e0"+
		"\u00e4\u00e7\u00ed\u00f1\u00f7\u00fb\u0101\u0105\u010b\u0111\u0113\u011a"+
		"\u011e\u0121\u0127\u012d\u012f\u0136\u013a\u013d\u0143\u0147\u014a\u0150"+
		"\u0154\u0157\u015d\u0161\u0167\u016b\u0171\u0176\u0179\u017f\u0183\u0189"+
		"\u018d\u0193\u019a\u019c\u019f\u01a2\u01a8\u01ac\u01b2\u01b6\u01bc\u01c1"+
		"\u01c8\u01cf\u01db\u01de\u01e4\u01ea\u01f2\u01f5\u01f8\u01fe\u0202\u0207"+
		"\u0214\u0219\u0228\u022d\u0236\u023b\u0244\u0247\u0251\u0256\u0263\u0268"+
		"\u0276\u027d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
