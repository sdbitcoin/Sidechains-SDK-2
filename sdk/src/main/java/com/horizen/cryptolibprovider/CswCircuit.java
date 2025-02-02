package com.horizen.cryptolibprovider;

import com.horizen.block.WithdrawalEpochCertificate;
import com.horizen.box.Box;
import com.horizen.librustsidechains.FieldElement;
import com.horizen.params.NetworkParams;
import com.horizen.proposition.Proposition;
import com.horizen.secret.PrivateKey25519;
import com.horizen.utils.ForwardTransferCswData;
import com.horizen.utils.UtxoCswData;
import scala.Enumeration;

import java.util.List;
import java.util.Optional;

public interface CswCircuit {
    int utxoMerkleTreeHeight();

    FieldElement getUtxoMerkleTreeLeaf(Box<Proposition> box);

    byte[] getCertDataHash(WithdrawalEpochCertificate cert, Enumeration.Value sidechainCreationVersion) throws Exception;

    int rangeSize(int withdrawalEpochLength);

    boolean generateCoboundaryMarlinSnarkKeys(int withdrawalEpochLen, String provingKeyPath, String verificationKeyPath);

    byte[] privateKey25519ToScalar(PrivateKey25519 pk);

    byte[] utxoCreateProof(UtxoCswData utxo,
                           WithdrawalEpochCertificate lastActiveCert,
                           byte[] mcbScTxsCumComEnd,
                           byte[] receiverPubKeyHash,
                           PrivateKey25519 pk,
                           int withdrawalEpochLength,
                           byte[] constant,
                           byte[] sidechainId,
                           String provingKeyPath,
                           boolean checkProvingKey,
                           boolean zk,
                           Enumeration.Value sidechainCreationVersion) throws Exception;

    byte[] ftCreateProof(ForwardTransferCswData ft,
                         Optional<WithdrawalEpochCertificate> lastActiveCertOpt,
                         byte[] mcbScTxsCumComStart,
                         List<byte[]> scTxsComHashes,
                         byte[] mcbScTxsCumComEnd,
                         byte[] receiverPubKeyHash,
                         PrivateKey25519 pk,
                         int withdrawalEpochLength,
                         byte[] constant,
                         byte[] sidechainId,
                         String provingKeyPath,
                         boolean checkProvingKey,
                         boolean zk,
                         Enumeration.Value sidechainCreationVersion) throws Exception;
}
